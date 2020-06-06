package com.example.intergration.motel.controller;

import com.example.intergration.motel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "Hello Motherfuckers";
    }

    @GetMapping("/sinhvien")
    public String sinhvien(){
        return "Welcome user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome admin";
    }

}
