package com.ohgiraffers.section02.formdata;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet("/formdata")
public class FormDataTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCharacterEncoding());
//        req.setCharacterEncoding("UTF-8");
//        톰캣 10버전 부터는 위 과정을 해주지 않아도 자동으로 해준다.
        String name = req.getParameter("name");
        System.out.println("name = "+name);

        Map<String, String[]> requestMap = req.getParameterMap();
        Set<String> keySet =requestMap.keySet();
        Iterator<String> keyiter = keySet.iterator();

        while(keyiter.hasNext()){
            String key= keyiter.next();
            String[] values = requestMap.get(key);

            System.out.println("key : "+key);
            for(int i=0;i<values.length;i++){
                System.out.println("value : "+values[i]);
            }
        }

        Enumeration<String> names= req.getParameterNames();
        while(names.hasMoreElements()){
            System.out.println(names.nextElement());
        }

    }
}
