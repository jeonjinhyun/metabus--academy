package com.ohgiraffers.section03.proxy.Common;

public class OhgiraffersStudent implements Student{

    @Override
    public void study(int hours){
        System.out.println(hours + "시간 동안 열심히 공부합시다.");
    }
}
