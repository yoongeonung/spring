package jp.ac.hal.yoongeonung.spring_mvc.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // template
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }

    // template, use request param
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // api - return raw string
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // api - return object
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // return object
    @Getter
    @Setter
    static class Hello {
        private String name;
    }
}

