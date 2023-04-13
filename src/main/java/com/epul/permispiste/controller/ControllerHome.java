package com.epul.permispiste.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControllerHome {

    @GetMapping("/")
    public ModelAndView home() {
        System.out.println("home");
        return new ModelAndView("index");
    }
}
