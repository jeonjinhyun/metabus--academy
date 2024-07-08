package com.ohgiraffers.section01.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 서블릿이 하는 역할
        // 1. 요청 정보 받기
        // 2. 비지니스 로직 처리
        // 3. 응답 내보내기

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        System.out.println("userId = " + userId);
        System.out.println("password = " + password);

        String nickName = "";
        if(userId != null && password != null) {
            nickName = "홍길동";
        }

        req.setAttribute("nickName", nickName);

        RequestDispatcher rd = req.getRequestDispatcher("print");
        rd.forward(req, resp);
    }
}
