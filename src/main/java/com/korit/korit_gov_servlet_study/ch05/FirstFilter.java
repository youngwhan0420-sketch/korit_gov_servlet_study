package com.korit.korit_gov_servlet_study.ch05;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/ch05/*") //ch05뒤에 오는 모든 경로를 이 필터를 적용시킨다.
public class FirstFilter implements Filter { //필터는 기본적으로 인터페이스로 되어있다.


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("필터 초기화");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //리퀘스트, 리스폰스, 체인이 들어오고 이싿.
        System.out.println("필터(전처리): 요청 들어오는 중");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("필터(후처리): 응답 나가는 중");
    }
    @Override
    public void destroy() {
        System.out.println("필터 소멸");
    }

}
