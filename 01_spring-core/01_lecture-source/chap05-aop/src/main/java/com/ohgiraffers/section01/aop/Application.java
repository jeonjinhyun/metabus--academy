package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");

        MemberService memberService = (MemberService)context.getBean("memberService");

        System.out.println("findAllMembers ===============================");
        System.out.println(memberService.findAllMembers());
        System.out.println("findMemberById ===============================");
        System.out.println(memberService.findMemberById(1L));
    }

}
