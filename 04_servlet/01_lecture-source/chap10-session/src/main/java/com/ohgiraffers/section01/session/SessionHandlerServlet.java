package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        //  사용자별로 한개의 세션을 만들어준다.
        HttpSession session = req.getSession();
        System.out.println("sesstion id = " + session.getId());

        System.out.println("session default 유지 시간 : " +session.getMaxInactiveInterval());
        session.setMaxInactiveInterval(60*10);//세션의 만료 시간을 10분으로 설정

        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);

        session.invalidate();

        resp.sendRedirect("redirect");
    }
}
