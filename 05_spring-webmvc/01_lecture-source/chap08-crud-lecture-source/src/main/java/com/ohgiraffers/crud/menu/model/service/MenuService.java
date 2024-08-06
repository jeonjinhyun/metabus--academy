package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;
    private final ConversionService conversionService;

    public MenuService(MenuMapper menuMapper, ConversionService conversionService) {
        this.menuMapper = menuMapper;
        this.conversionService = conversionService;
    }

    public List<MenuDTO> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }

    @Transactional
    public void registNewMenu(MenuDTO newMenu) {menuMapper.registNewMenu(newMenu);
    }
}
