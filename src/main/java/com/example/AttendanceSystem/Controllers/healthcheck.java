package com.example.AttendanceSystem.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class healthcheck {

    @GetMapping("/hi")
    public String healthcheck(){
        return "ok";
    }

}
