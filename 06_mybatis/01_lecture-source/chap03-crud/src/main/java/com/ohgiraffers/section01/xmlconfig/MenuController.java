package com.ohgiraffers.section01.xmlconfig;

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

    public void findMenuByMenuCode(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));

        MenuDTO menu = menuService.findMenuByMenuCode(menuCode);
        if (menu != null) {
            printResult.printMenu(menu);
        }else{
            printResult.printErrorMessage("findOne");
        }
    }

    public void registMenu(Map<String, String> parameter) {
        String menuName = parameter.get("menuName");
        int menuPrice = Integer.parseInt(parameter.get("menuPrice"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setCategoryCode(categoryCode);

        if(menuService.registMenu(menu)){
            printResult.printSuccessMessage("insert");
        }else{
            printResult.printErrorMessage("insert");
        }
    }

    public void modifyMenu(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));
        String menuName = parameter.get("menuName");
        int menuPrice = Integer.parseInt(parameter.get("menuPrice"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setMenuCode(menuCode);
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setCategoryCode(categoryCode);

        if(menuService.modifyMenu(menu)){
            printResult.printSuccessMessage("modify");
        }else{
            printResult.printErrorMessage("modify");
        }

    }

    public void removeMenu(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));

        if(menuService.removeMenuByMenuCode(menuCode)){
            printResult.printSuccessMessage("remove");
        }else{
            printResult.printErrorMessage("remove");
        }
    }
}
