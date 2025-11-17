package com.korit.korit_gov_servlet_study.ch07;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BoardReqDto {
    private String title;
    private String content;
    private String username;

    public Board toEntity() {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
    }
}
