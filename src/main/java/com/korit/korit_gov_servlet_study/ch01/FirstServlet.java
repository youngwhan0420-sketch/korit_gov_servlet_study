package com.korit.korit_gov_servlet_study.ch01;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/*
* 서블릿
* 클라이언트의 요청을 처리하고 그 결과를 반환하는 Servlet클래스의 구현
* 규칙을 지킨 자바 웹 프로그래밍
* */
public class FirstServlet extends HttpServlet {

    /*
    * 이닛은 서블릿 초기화 메소드이다. 컨테이너가 딱 한번 호출한다.
    * 라이프 사이클은 생명주기다. 언제 생겨나고 언제 없어지는지 이 생명주기의 순서는
    * 생성자호출 (생성자가 호출됐다는건 객ㅊ체가 생성이 됐다. 객체가 생성됨에 따라  -> init()(1회만 호출됨) -> 요청마다 service()를 호출한다.
    *  ->호출되면 요청 메소드에 따라서 -> doGet,doPost로 메소드가 호출된다.
    * 톰캣 서버가 꺼지면 그때 destroy()가 된다. */
    public FirstServlet() {
        System.out.println("FirstServlet 생성자 호출");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init 메소드 호출 초기화");
        //init에 서블릿컨텍스트라는걸 쓴다.
        config.getServletContext().setAttribute("age", 27);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  super.service(req, resp);
        System.out.println("service 메소드 호출 요청 들어옴");
    } //처음 요청이 들어오면



    @Override
    public void destroy() {
        System.out.println("destroy 메소드 호출 소멸");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
