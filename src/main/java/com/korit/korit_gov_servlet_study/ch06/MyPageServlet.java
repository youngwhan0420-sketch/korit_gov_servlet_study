package com.korit.korit_gov_servlet_study.ch06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch06/mypage/home")
public class MyPageServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("마이 페이지 요청 들어옴");
        resp.setContentType("text/plain");
        resp.getWriter().write("마이 페이지 입니다.");

    }
}
