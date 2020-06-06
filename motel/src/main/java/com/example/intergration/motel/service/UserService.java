package com.example.intergration.motel.service;

import com.example.intergration.motel.beans.User;

import java.util.List;

public interface UserService {
    User getUserById( int id );

    User getUserByUsername( String username );

    List<User> getUserByGroup( String group );
}
