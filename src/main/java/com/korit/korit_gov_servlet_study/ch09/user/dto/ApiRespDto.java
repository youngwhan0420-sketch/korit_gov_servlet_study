package com.korit.korit_gov_servlet_study.ch09.user.dto;

import lombok.Builder;

@Builder

public class ApiRespDto<T> {
    private String status;
    private String message;
    private T body;
}
