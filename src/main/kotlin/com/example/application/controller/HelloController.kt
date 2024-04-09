package com.example.application.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class HelloController {
    @GetMapping("/hello")
    fun helloController() = ModelAndView().apply{
        viewName = "hello"
    }
}