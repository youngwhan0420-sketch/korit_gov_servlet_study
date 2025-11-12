package com.korit.korit_gov_servlet_study.ch01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletConfigTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Object age = req.getServletContext().getAttribute("age");
        System.out.println(age);
    }
}
//실행하고 주소창에 ch01/config/age 치면 널이 뜨는 이유는 FirstServlet호출이 된적이 없어서 init이 호출이 안됨 그래서 null뜬다.
// 그래서 셋해줘야