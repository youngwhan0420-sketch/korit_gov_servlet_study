package com.korit.korit_gov_servlet_study.ch02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/*
* HTTP 프로토콜 메소드
* 1.GET
* = 용도는 리소스(어떤 데이터) 조회하는데 사용한다.
* = 특징은 서버로부터 데이터를 요청만 하고 수정은 하지 않는다. , 요청 데이터(파라미터)가 URL에 활용된다.
* = 멱등성이 있다. 여러번 똑같은 요청을 보내도 똑같은 결과가 온다.
*
* 2. POST (데이터 처리)
* = 용도는 새로운 리소스 생성
* = 특징은 서버에 데이터를 전송하여 새로운 리소스 생성
* = 요청 데이터가 HTTP Body에 포함된다.
* = 멱등성 없다.
*
* 3. PUT
* = 용도는 리소스 전체 수정/생성
* = 특징은 리소스가 있으면 전체를 교체, 없으면 생성
* = 그래서 전체 데이터를 전송해야함
* = 멱등성 있다.
*
* 4. PATCH
* = 용도는 리소스 부분 수정
* = 특징은 리소스의 일부만 수정한다. PUT보다는 효율적이다.(변경할 필드만 전송해준다.)
* = 멱등성이 없다.
*
* 5. DELETE
* = 용도는 리소스 삭제
* = 특징은 지정된 리소스를 삭제한다.
* = 멱등성이 있다.
*
* 6. HEAD
* = 용도는 리소스 존재 여부 또는 메타 데이터 확인
*
* 7. OPTIONS
* = 용도는 HTTP메소드의 존재여부 또는 CORS 프리플라이트 요청에 사용
*
* 8. CONNECT
* = 용도는 프록시(대리자 서버)서버를 통한 터널링에 사용, SSL 연결에 사용
*
* 9. TRACE
* = 용도는 디버깅*/
@WebServlet("/ch02/method")
public class HttpMethodServlet extends HttpServlet {
    Map<String, String> datas = new HashMap<>(Map.of(
            "name", "김영환",
            "age", "29",
            "address", "울산시"
    ));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET요청 들어옴");

        System.out.println("요청 메소드: " + req.getMethod());
        System.out.println("요청 쿼리 파라미터(datasKey): " + req.getParameter("datasKey"));
        String datasKey = req.getParameter("datasKey");

        System.out.println(datas.get("datasKey"));

        //응답, 응답에 대한 객체는 resp
        resp.setContentType("text/html");
        //한글 깨지는걸 방지 해준다.
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //타입을 잡아준다.
        PrintWriter out = resp.getWriter(); //문자 출력용
        out.println(datas.get(datasKey)); //???로 나오는 이유 한글 인코딩 문제다 한글이 깨져서 온다. 얘를 해결할려면


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST요청 들어옴");

        System.out.println("요청 메소드: " + req.getMethod());
        System.out.println("요청 쿼리 파라미터(KeyName): " + req.getParameter("KeyName"));
        System.out.println("요청 쿼리 파라미터(value): " + req.getParameter("value"));
        datas.put(req.getParameter("KeyName"), req.getParameter("value"));

        System.out.println(datas.toString());

        //응답 해주기
        resp.setStatus(201); //리소스가 잘 생성 되었다.
        resp.setContentType("text/plain"); //단순 텍스트를 응답으로 넣어주겠다.
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println("데이터 추가 성공!!");
    }
}



