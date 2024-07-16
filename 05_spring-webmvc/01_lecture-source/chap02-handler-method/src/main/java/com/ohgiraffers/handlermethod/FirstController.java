package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")
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

    @GetMapping("modify")
    public void modify() {}

    @PostMapping("modify")
    public String modifyMenuPrice(Model model,
                                  String modifyName,
                                  int modifyPrice) {

        System.out.println("modifyName = " + modifyName);
        System.out.println("modifyPrice = " + modifyPrice);

        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "원으로 가격을 변경하였습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @PostMapping("modifyAll")
    public String modifyAll(Model model, @RequestParam Map<String, String> parameters) {

        String modifyName = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = "메뉴의 이름을 " + modifyName + "(으)로, 가격을 " + modifyPrice + "원 으로 변경하였습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search() {}

    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu) {

        System.out.println(menu);

        return "first/searchResult";
    }

    @GetMapping("login")
    public void login() {}

    @PostMapping("login1")
    public String sessionTest1(HttpSession session,@RequestParam String id) {

        session.setAttribute("id", id);


        return "first/loginResult";
    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {
//        session.removeAttribute("id");
//        session.setMaxInactiveInterval(600);
        session.invalidate();

        return "first/loginResult";
    }

    @PostMapping("login2")
    public String sessionTest2(Model model,@RequestParam String id) {

        model.addAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout2")
    public String logoutTest2(SessionStatus status){

        status.setComplete();

        return "first/loginResult";
    }

    @GetMapping("body")
    public void body(){
    }

    @PostMapping("body")
    public void bodyTest(@RequestBody String body,
                         @RequestHeader("content-type") String contentType,
                         @CookieValue(value = "JSESSIONID", required = false) String sessionId) throws UnsupportedEncodingException {

        System.out.println(URLDecoder.decode(body,"UTF-8"));
        System.out.println(contentType);
        System.out.println(sessionId);
    }
}
