package com.malinduliyanage.books.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//We use @Controller to return a View
@Controller
//RequestMapping maps a specific URL path to a class or method, helps to group related endpoints under one base path.
@RequestMapping("/")
public class HomeController {

    //Reads metadata from application.yaml
    @Value("${spring.application.name}")
    private String successMsg;

    public String index () {

        System.out.println(successMsg);
        return "index.html";
    }
}
