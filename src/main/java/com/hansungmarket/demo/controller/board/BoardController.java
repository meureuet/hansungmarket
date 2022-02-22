package com.hansungmarket.demo.controller.board;

import com.hansungmarket.demo.dto.board.BoardRequestDto;
import com.hansungmarket.demo.dto.board.BoardResponseDto;
import com.hansungmarket.demo.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    // 게시글 저장
    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestPart(value = "board") @Valid BoardRequestDto requestDto,
                                        @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {
        return boardService.create(requestDto, images);
    }

    // 게시글 리스트 출력
    @GetMapping("/boards")
    public List<BoardResponseDto> searchAllBoards(@RequestParam(required = false) String category) {
        // 전체검색
        if (StringUtils.isEmpty(category)) {
            return boardService.searchAll();
        }

        // 카테고리 검색
        return boardService.searchByCategory(category);
    }

    // id에 해당하는 게시글 출력
    @GetMapping("/boards/{id}")
    public BoardResponseDto searchBoardById(@PathVariable Long id) {
        return boardService.searchById(id);
    }

    // 게시글 수정
    @PutMapping("/boards/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,
                                        @RequestPart(value = "board") BoardRequestDto requestDto,
                                        @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {
        return boardService.updateBoard(id, requestDto, images);
    }
    
    //게시글 삭제
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
    }

}
