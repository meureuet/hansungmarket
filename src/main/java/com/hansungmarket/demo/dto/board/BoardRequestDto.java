package com.hansungmarket.demo.dto.board;

import com.hansungmarket.demo.entity.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;

    private String goodsName;

    private String goodsCategory;

    private String content;

    @Builder
    private BoardRequestDto(String title, String goodsName, String goodsCategory, String content) {
        this.title = title;
        this.goodsName = goodsName;
        this.goodsCategory = goodsCategory;
        this.content = content;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .goodsName(goodsName)
                .goodsCategory(goodsCategory)
                .content(content)
                .build();
    }
}
