package com.malinduliyanage.books.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//We use @Controller to return a View
@Controller
//RequestMapping maps a specific URL path to a class or method, helps to group related endpoints under one base path.
@RequestMapping("/")
public class HomeController {

    public String index () {
        return "index.html";
    }
}
