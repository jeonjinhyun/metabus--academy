package com.ohgiraffers.persistence.section03.entity;

import com.ohgiraffers.persistence.section02.crud.EntityManagerGenerator;

import javax.persistence.EntityManager;

public class EntityLifecycle {
    private EntityManager manager;

    public Menu findMenuByMenuCode(int menuCode) {
        manager = EntityManagerGenerator.getManagerInstance();

        return manager.find(Menu.class, menuCode);
    }
}
