package com.ohgiraffers.section03.remix;

import java.util.List;
import java.util.Map;

public class MenuController {
    private final PrintResult printResult;
    private final MenuService menuService;
    public MenuController(){
        printResult = new PrintResult();
        menuService = new MenuService();
    }
    //controller 는 요청에 맞는 service를 호출하고 그 결과를 view와 연결해 줍니다.
    public void findMenuAll(){
        List<MenuDTO> menuList = menuService.findAllMenus();

        if(menuList !=null){
            printResult.printMenuList(menuList);
        }else{
            printResult.printErrorMessage("findAll");
        }
    }

    public void findMenuByMenuCode(Map<String, String> stringStringMap) {
    }

    public void registMenu(Map<String, String> stringStringMap) {
    }

    public void modifyMenu(Map<String, String> stringStringMap) {
    }

    public void removeMenu(Map<String, String> stringStringMap) {
    }
}
