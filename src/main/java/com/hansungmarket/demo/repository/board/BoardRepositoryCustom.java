package com.hansungmarket.demo.repository.board;

import com.hansungmarket.demo.entity.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findByUsername(String username);
}
