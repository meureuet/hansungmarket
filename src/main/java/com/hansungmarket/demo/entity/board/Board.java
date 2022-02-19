package com.hansungmarket.demo.entity.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_category")
    private String goodsCategory;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "board",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<BoardImage> boardImages = new ArrayList<>();

    @Builder
    private Board(String title, String goodsName, String goodsCategory, String content) {
        this.title = title;
        this.goodsName = goodsName;
        this.goodsCategory = goodsCategory;
        this.content = content;
    }
    
    // 게시글에 해당하는 이미지 리스트 저장
    public void setBoardImages(List<BoardImage> boardImages) {
        this.boardImages = boardImages;
    }
    
    // 게시글 업데이트 기능
    public void update(String title, String goodsName, String goodsCategory, String content, List<BoardImage> boardImages) {
        this.title = title;
        this.goodsName = goodsName;
        this.goodsCategory = goodsCategory;
        this.content = content;
        this.boardImages = boardImages;
    }
}
