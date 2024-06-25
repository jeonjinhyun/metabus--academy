package com.ohgiraffers.section03.proxy.subsection02.cglib;

import com.ohgiraffers.section03.proxy.Common.OhgiraffersStudent;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class Handler implements InvocationHandler {

    public  final OhgiraffersStudent student;

    public Handler(OhgiraffersStudent student) {
        this.student = student;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("공부가 너무 하고싶어요");

        method.invoke(student, args);

        System.out.println("왜 벌써 자려구요? 너렙에 잡이와요? 네!");

        return null;
    }
}
