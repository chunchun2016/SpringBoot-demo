package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 17/4/24.
 */
@Controller
public class IndexController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "https://github.com/chunchun2016");
        return "home";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
