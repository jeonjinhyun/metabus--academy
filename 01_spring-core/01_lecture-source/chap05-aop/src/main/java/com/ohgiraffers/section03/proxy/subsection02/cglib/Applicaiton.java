package com.ohgiraffers.section03.proxy.subsection02.cglib;

import com.ohgiraffers.section03.proxy.Common.OhgiraffersStudent;
import org.springframework.cglib.proxy.Enhancer;

public class Applicaiton {

    public static void main(String[] args) {

        Handler handler =  new Handler(new OhgiraffersStudent());
        OhgiraffersStudent proxy = (OhgiraffersStudent) Enhancer.create(OhgiraffersStudent.class,handler);

        proxy.study(20);
    }
}
