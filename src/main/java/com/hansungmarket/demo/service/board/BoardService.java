package com.hansungmarket.demo.service.board;

import com.hansungmarket.demo.dto.board.BoardRequestDto;
import com.hansungmarket.demo.dto.board.BoardResponseDto;
import com.hansungmarket.demo.entity.board.Board;
import com.hansungmarket.demo.entity.board.BoardImage;
import com.hansungmarket.demo.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardImageService boardImageService;

    // 모든 게시글 검색
    @Transactional(readOnly = true)
    public List<BoardResponseDto> searchAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    // id로 게시글 검색
    @Transactional(readOnly = true)
    public BoardResponseDto searchById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        return new BoardResponseDto(board);
    }

    // 카테고리 검색
    @Transactional(readOnly = true)
    public List<BoardResponseDto> searchByCategory(String category) {
        return boardRepository.findByGoodsCategory(category).stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시글 생성(이미지 X)
    @Transactional
    public BoardResponseDto create(BoardRequestDto requestDto) {
        Board board = boardRepository.save(requestDto.toEntity());
        return new BoardResponseDto(board);
    }

    // 게시글 생성(이미지 O)
    @Transactional
    public BoardResponseDto create(BoardRequestDto requestDto, List<MultipartFile> images) throws IOException {
        // 게시글 저장
        Board board = boardRepository.save(requestDto.toEntity());

        // 이미지가 없는 경우
        if (CollectionUtils.isEmpty(images)) {
            return new BoardResponseDto(board);
        }

        // 이미지가 있는 경우
        List<BoardImage> boardImages = new ArrayList<>();
        for (MultipartFile image : images) {
            // 이미지 저장
            BoardImage boardImage = boardImageService.create(board, image);
            boardImages.add(boardImage);
        }
        board.setBoardImages(boardImages);

        return new BoardResponseDto(board);
    }

    // 게시글 수정(이미지 O)
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, List<MultipartFile> images) throws IOException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        List<BoardImage> boardImages = boardImageService.searchByBoardId(id);

        // 수정 전 게시글에 이미지 존재하면 전부 삭제
        if (!CollectionUtils.isEmpty(boardImages)) {
            // 이미지 파일 삭제
            for (BoardImage image : boardImages) {
                boardImageService.deleteFile(image);
            }
            // DB에 저장된 이미지 정보 삭제
            boardImageService.deleteByBoardId(id);
        }

        // 수정 후 게시글에 이미지 존재하면 삽입
        if (!CollectionUtils.isEmpty(images)) {
            for (MultipartFile image : images) {
                boardImageService.create(board, image);
            }
        }

        // 게시글 수정사항 업데이트
        board.update(requestDto.getTitle(),
                requestDto.getGoodsName(),
                requestDto.getGoodsCategory(),
                requestDto.getContent(),
                board.getBoardImages());

        return new BoardResponseDto(board);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long id) {
        List<BoardImage> boardImages = boardImageService.searchByBoardId(id);

        // 게시글에 이미지가 없는 경우
        if (CollectionUtils.isEmpty(boardImages)) {
            // 게시글 삭제
            boardRepository.deleteById(id);
            return;
        }

        //게시글에 이미지가 있는 경우
        for (BoardImage boardImage : boardImages) {
            // 이미지 파일 삭제
            boardImageService.deleteFile(boardImage);
        }

        // 게시글 삭제
        boardRepository.deleteById(id);
    }
}
