package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.User;
import com.example.intergration.motel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "Hello ";
    }

    @GetMapping("/sinhvien")
    public String sinhvien(){
        return "Welcome user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome admin";
    }


    @GetMapping("/api/user/{id}")
    public ResponseEntity<Optional<User>> getUser(
    @PathVariable(name = "id") int id
    ){
        return ResponseEntity.ok(
                userRepository.findById(id)
        );
    }


}
