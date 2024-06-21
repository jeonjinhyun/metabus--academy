package com.ohgiraffers.section02.initdestroy.subsection02.annotation;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Owner {
    @PostConstruct
    public void openShop(){
        System.out.println("사장님이 가게문을 열었습니다. 이제 쇼핑을 시작할 수 있습니다.");
    }
    @PreDestroy
    public void closeShop(){
        System.out.println("사장님이 가게문을 닫았습니다. 이제 쇼핑을 할 수 없습니다.");
    }
}
