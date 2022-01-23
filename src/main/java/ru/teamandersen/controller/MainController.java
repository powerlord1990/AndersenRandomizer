package ru.teamandersen.controller;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.controller 
*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("")
    public String test(){
        return "hello";
    }
}
