package jp.ac.hal.yoongeonung.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCotreller {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
