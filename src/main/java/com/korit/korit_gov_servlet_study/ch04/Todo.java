package com.korit.korit_gov_servlet_study.ch04;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor


public class Todo {
    private Integer todoId;
    private String title;
    private String content;
    private String username;

}
