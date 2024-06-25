package com.ohgiraffers.section03.proxy.subsection01.dynamic;

import com.ohgiraffers.section03.proxy.Common.OhgiraffersStudent;
import com.ohgiraffers.section03.proxy.Common.Student;

import java.lang.reflect.Proxy;

public class Application {

    public static void main(String[] args) {
        // 프록시(proxy)란?
        // 실제 Target의 기능을 수행하면서 기능을 확장하거나 추가하는 실제 객체를 의미한다.

        Student student = new OhgiraffersStudent();
        Handler handler = new Handler(student);

        Student proxy = (Student) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[] {Student.class}, handler);
        proxy.study(16);
    }
}
