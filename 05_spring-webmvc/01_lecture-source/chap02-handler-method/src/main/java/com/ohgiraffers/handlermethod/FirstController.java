package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/first/*")
public class FirstController {

    // /first/regist 요청이 들어오면
    // void 메소드인 경우 요청 주소가 곧 view의 이름이 된다.
    // return "/first/regist" 를 작성해주는 것과 같다.
    @GetMapping("regist")
    public void regist() {}
    
    /* 1. WebRequest로 요청 파라미터 전달 받기
    * */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        String menuName = request.getParameter("menuName");
        int menuPrice = Integer.parseInt(request.getParameter("menuPrice"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = menuName + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 "
                + menuPrice + "원으로 등록하셨습니다!";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }
}
