package com.korit.korit_gov_servlet_study.ch02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/ch02/users")

public class UserServlet extends HttpServlet {
    private List<User> users;
    private String Username;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>(); //이게 먼저 일어난다. 왜 이닛에 해주냐면 이 서블릿이 최초의 이닛이 되면 서버가 꺼지기 전까지 리스트가 켜져있기 때문
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //넣기
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());//집어넣기
        String username = req.getParameter("username");//유저에 있는 4가지 데이터를 요청받는다.
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = User.builder()//유저 객체를 만든다.
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();

        Map<String, String> error = validUser(user);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 에러 응답
        if (!error.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(error);
            return;
        }

        validUser(user);

        users.add(user);
        System.out.println(users);
        //응답해주기
       //정상 처리 응답
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("사용자 등록 완료");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //조회하기
        //username으로 user찾기, 찾으면 user객체 응답(200), 없으면 username은 존재하지 않습니다.(404)
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());//집어넣기
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();

        User foundUser = foundUsers.isEmpty() ? null : foundUsers.get(0);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (foundUser == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username은 존재하지 않습니다.");
            return;
        }
        resp.getWriter().println(foundUser);

    }


    private Map<String, String> validUser(User user) {
        Map<String, String> error = new HashMap<>();

        //유저 안에있는 필드, 유저가 아니여도 다른곳에서도 쓸수 있게 만들기
        //user객체의 선언된 모든 필드 (접근제어자 무관)를 스트림으로 순환한다.
        Arrays.stream(user.getClass().getDeclaredFields()).forEach(f -> {
            //private 필드에도 접근할 수 있게 강제로 접근 허용
            f.setAccessible(true);
            String fieldName = f.getName();
            System.out.println(fieldName);

            try {
                //리플렉션으로 user인스턴ㄴ스의 해당 필드값 꺼내기
                Object fieldValue = f.get(user);
                System.out.println(fieldValue);
                //만약 해당 필드값이 null이면 검증 실패로 간주
                if (fieldValue == null) {
                    throw new RuntimeException();
                }
                //필드값이 문자열일때 공백/빈 문자열이면 실패로 간주
                if (fieldValue.toString().isBlank()) {
                    throw new RuntimeException();
                }
            } catch (IllegalAccessException e) {
                //필드 접근 권한 문제인데(드물게 발생한다.)
                System.out.println("필드에 접근할 수 없습니다.");
            } catch (RuntimeException e) {
                //위에서 던진 예외를 여기서 받아서 해당 필드에 대한 에러메시지 추가
                error.put(fieldName, "빈 값일 수 없습니다.");
            }

        });
        //validUser를 어디다 쓰냐면 36번중
        return error;
    }
}
