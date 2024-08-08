package com.ohgiraffers.association.section01.manytoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuFindService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuFindService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findMenuByMenuCode(int menuCode) {

        return menuRepository.findMenuByMenuCode(menuCode);
    }
}
