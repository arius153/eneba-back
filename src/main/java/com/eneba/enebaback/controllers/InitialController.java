package com.eneba.enebaback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InitialController {

    @GetMapping
    private String getMessage() {
        return "Hi";
    }
}
