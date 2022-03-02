package com.hansungmarket.demo.repository.board;

import com.hansungmarket.demo.entity.board.Board;
import com.hansungmarket.demo.entity.board.QBoard;
import com.hansungmarket.demo.entity.user.QUser;

import java.util.List;

public class BoardRepositoryImpl implements BoardRepositoryCustom{
    @Override
    public List<Board> findByUsername(String username) {
        QBoard board = QBoard.board;
        QUser user = QUser.user;

        return null; // 나중에 수정
    }
}
