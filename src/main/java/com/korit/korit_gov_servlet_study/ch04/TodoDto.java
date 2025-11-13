package com.korit.korit_gov_servlet_study.ch04;


import lombok.Builder;

@Builder
public class TodoDto {
    private String title;
    private String content;
    private String username;

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();
    }
}
