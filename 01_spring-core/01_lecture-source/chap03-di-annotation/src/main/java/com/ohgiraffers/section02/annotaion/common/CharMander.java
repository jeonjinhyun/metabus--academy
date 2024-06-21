package com.ohgiraffers.section02.annotaion.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CharMander implements Pokemon{

    @Override
    public void attack() {
        System.out.println("파이리 불꽃 공격!!");
    }
}
