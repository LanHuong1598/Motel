package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.LogInRequest;
import com.example.intergration.motel.beans.User;
import com.example.intergration.motel.exception.UserNotFoundException;
import com.example.intergration.motel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class LogController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(
            @RequestBody User user
    ){
        try{
            return new ResponseEntity<>(
                    userRepository.save(user),
                    HttpStatus.OK
            );
        }
        catch ( Exception e ){
            return new ResponseEntity<>( HttpStatus.EXPECTATION_FAILED );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> logIn(
            @RequestBody LogInRequest logInRequest
    ){
        Optional<User> user = userRepository.findByAcc( logInRequest.getUsername());
        if( user.isPresent() ){
            if (!user.get().getPassword().equals(logInRequest.getPassword()) ){
                throw new UserNotFoundException("Password incorrect!");
            }
            else{
                return ResponseEntity.ok( user.get());
            }
        }
        else{
            throw new UserNotFoundException("Username incorrect!");
        }
    }

    //Sửa thông tin người dùng
    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(
            @PathVariable(name = "id") int id,
            @RequestBody User user
    ){
        Optional<User> _user = userRepository.findByIduser(id);
        if (_user.isPresent() ){
            _user.get().setAcc(user.getAcc());
            _user.get().setPassword(user.getPassword());
            return ResponseEntity.ok(
                    _user.get()
            );
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
