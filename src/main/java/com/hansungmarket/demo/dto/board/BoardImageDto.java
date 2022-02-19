package com.hansungmarket.demo.dto.board;

import com.hansungmarket.demo.entity.board.Board;
import com.hansungmarket.demo.entity.board.BoardImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardImageDto {
    private String originalFileName;

    private String storedFileName;

    private String storedFilePath;

    private Board board;

    @Builder
    private BoardImageDto(String originalFileName, String storedFileName, String storedFilePath, Board board) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.storedFilePath = storedFilePath;
        this.board = board;
    }

    public BoardImage toEntity() {
        return BoardImage.builder()
                .originalFileName(originalFileName)
                .storedFileName(storedFileName)
                .storedFilePath(storedFilePath)
                .board(board)
                .build();
    }
}
