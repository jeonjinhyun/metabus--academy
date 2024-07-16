package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Stream;

@Controller
public class MainController {


    @RequestMapping(value={"/","/main"})
    public String main() {
        return "main";
    }
}
