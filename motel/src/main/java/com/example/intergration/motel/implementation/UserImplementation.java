package com.example.intergration.motel.implementation;

import com.example.intergration.motel.beans.User;
import com.example.intergration.motel.exception.UserNotFoundException;
import com.example.intergration.motel.repository.UserRepository;
import com.example.intergration.motel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findByIduser(id);
        if( user.isPresent() ){
            return user.get();
        }
        throw new UserNotFoundException("User Not Found!");
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByAcc(username);
        if( user.isPresent() ){
            return user.get();
        }
        throw new UserNotFoundException("User Not Found!");
    }

    @Override
    public List<User> getUserByGroup(String group) {
        return userRepository.findAllByIdgroup(group);
    }

    public String getUserGroup( int id ){
        User user = getUserById(id);
        return user.getIdgroup();
    }
}
