package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserServiceRepository extends JpaRepository<UserService, Integer> {
    List<UserService> findByIduser( int id );

}
