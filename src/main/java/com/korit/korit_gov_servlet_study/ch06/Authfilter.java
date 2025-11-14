package com.korit.korit_gov_servlet_study.ch06;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//마이페이지로 시작하는애들은 여기를 거치도록 함
public class Authfilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        System.out.println("[AuthFilter] 전처리 - 로그인 검증");
        Object principal = httpReq.getSession().getAttribute("principal");

        if (principal == null) {
            System.out.println("[AuthFilter] 로그인 안됨 -> 리다이렉트");
            httpResp.sendRedirect(httpReq.getContextPath() + "/ch06/login");
            return; //두 필터로 안가고 여기서 막힌다.
        }
        filterChain.doFilter(httpReq, httpResp);

        System.out.println("[AuthFilter] 후처리 - 로그인된 사용자의 응답 처리");
    }
}
