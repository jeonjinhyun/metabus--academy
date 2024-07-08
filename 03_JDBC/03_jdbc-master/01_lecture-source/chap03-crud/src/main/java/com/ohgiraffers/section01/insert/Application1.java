package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정하실 메뉴의 코드 :");
        int menuCode = sc.nextInt();
        sc.nextLine();
        System.out.print("무슨 이름으로 수정하시겠어요 :");
        String menuName=sc.nextLine();
        MenuDTO menu = new MenuDTO();
        menu.setMenuCode(menuCode);
        menu.setMenuName(menuName);
        MenuRegistService menuRegistService = new MenuRegistService();
        if(menuRegistService.updateMenu(menu)){
            System.out.println("메뉴 수정에 성공하셨습니다!");
        }else{
            System.out.println("메뉴 수정에 실패하셨습니다.");
        }
    }
}
