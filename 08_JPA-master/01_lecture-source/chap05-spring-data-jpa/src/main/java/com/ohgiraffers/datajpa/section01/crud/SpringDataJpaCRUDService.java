package com.ohgiraffers.datajpa.section01.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpringDataJpaCRUDService {

    private SpringDataJpaCRUDRepository crudRepository;

    public SpringDataJpaCRUDService(SpringDataJpaCRUDRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public List<FoundMenuResponseDTO> findAllMenus() {

        return crudRepository.findAll()
                .stream()
                .map(FoundMenuResponseDTO::new)
                .toList();
    }

    public FoundMenuResponseDTO findMenuByMenuCode(int menuCode) {

        return new FoundMenuResponseDTO(crudRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new));
    }

    @Transactional
    public void registNewMenu(MenuRegistRequestDTO menuInfo) {

        Menu newMenu = new Menu(
                menuInfo.getMenuName(),
                menuInfo.getMenuPrice(),
                menuInfo.getCategoryCode(),
                menuInfo.getOrderableStatus()
        );

        crudRepository.save(newMenu);
    }

    @Transactional
    public void modifyMenuName(int menuCode, String menuName) {

        Menu foundMenu = crudRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
        foundMenu.setMenuName(menuName);
    }

    @Transactional
    public void removeMenu(int menuCode) {

        crudRepository.deleteById(menuCode);
    }
}
