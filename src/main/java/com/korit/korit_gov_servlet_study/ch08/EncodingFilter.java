package com.korit.korit_gov_servlet_study.ch08;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/ch07/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {//필터가 될꺼니까 implements 해주고 인코딩 서블린으로 간다.
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        System.out.println("요청 응답 인코딩 적용됨 ");
        filterChain.doFilter(servletRequest, servletResponse); //이걸 안적어주면 그다음 필터와 서블릿으로 가지않는다.


    }

}
