package com.korit.korit_gov_servlet_study.ch07;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Board { //받아오는 데이터가 3개니까
    private Long boardId;
    private String title;
    private String content;
    private String username;
}
