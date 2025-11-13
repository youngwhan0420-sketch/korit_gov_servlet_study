//package com.korit.korit_gov_servlet_study.ch03;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//import static java.util.Arrays.stream;
//
//@WebServlet("/ch03/users")
//public class UserServlet extends HttpServlet {
//    private UserRepository userRepository;
//    private Gson gson;
//
//    @Override
//    public void init() throws ServletException {
//        userRepository = UserRepository.getInstance();
//        gson = new GsonBuilder().create();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //json으로 전체조회 하기
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json'");
//        SuccessResponse<List<User>> successResponse = SuccessResponse.<List<User>>builder()
//                .status(200)
//                .message("조회 완료")
//                .body(userRepository.findAll())
//                .build();
//        String json = gson.toJson(successResponse);
//        resp.getWriter().write(json);
//
//
//
//    }
//    @Override //1. 등록 먼저 하기
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        UserDto userDto = gson.fromJson(req.getReader(), UserDto.class);
//        //유저 객체 뿌리기 dto 가서 데이터하나 넣어주기
//
//        User foundUser = userRepository.findByUsername(userDto.getUsername());
//
//        resp.setContentType("application/json"); //응답을 제이슨으로 하겠다
//
//        if (foundUser != null) { //널이 아니라는건 중보고딘 유저네임을 사용했다는거니까
//            ErrorResponse errorResponse = ErrorResponse.builder()
//                    .message("이미 존재하는 username입니다.")
//                    .build();
//            resp.setStatus(400);
//            String json = gson.toJson(errorResponse);
//            resp.getWriter().write(json);
//            return;
//        }
//
//       StringBuilder sb = new StringBuilder();
//       BufferedReader reader = req.getReader();
//
//       String line;
//       while ((line = reader.readLine()) != null) {
//           sb.append(line).append("\n");
//       }
//        System.out.println(sb);
//
//
//    }
//}
package com.korit.korit_gov_servlet_study.ch03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/ch03/users")
public class UserServlet extends HttpServlet {
    private UserRepository userRepository;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getInstance();
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        SuccessResponse<List<User>> successResponse = SuccessResponse.<List<User>>builder()
                .status(200)
                .message("조회 완료")
                .body(userRepository.findAll())
                .build();
        String json = gson.toJson(successResponse);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        UserDto userDto = gson.fromJson(req.getReader(), UserDto.class);

        User foundUser = userRepository.findByUsername(userDto.getUsername());

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        if (foundUser != null) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(400)
                    .message("이미 존재하는 username입니다.")
                    .build();
            resp.setStatus(400);
            String json = gson.toJson(errorResponse);
            resp.getWriter().write(json);
            return;
        }

        User user = userRepository.addUser(userDto.toEntity());

        SuccessResponse<User> successResponse = SuccessResponse.<User>builder()
                .status(200)
                .message("사용자 등록이 완료되었습니다.")
                .body(user)
                .build();
        String json = gson.toJson(successResponse);
        resp.setStatus(200);
        resp.getWriter().write(json);
    }

}