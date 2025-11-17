package com.korit.korit_gov_servlet_study.ch07;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository { //리스트가 저장되어있는 레퍼지토리 생성
    private static BoardRepository instance;
    private List<Board> boards;
    private Long boardId; //int를 넣으면 board가 int범위를 넘어설 수 있기 때문에

    private BoardRepository() {
        boards = new ArrayList<>();
    }
    public static BoardRepository getInstance() {
        if (instance == null) {
            instance = new BoardRepository();
        }
        return instance; //싱글톤
    }
    public Board addBoard(Board board) {
        board.setBoardId(boardId++);
        boards.add(board);
        return board;
    }
    public List<Board> getBoardsListAll() {
        return boards;
    }
}
