package com.jgh.calendarjx.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @RequestMapping("/error")
    public String error() {
        return "Error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
