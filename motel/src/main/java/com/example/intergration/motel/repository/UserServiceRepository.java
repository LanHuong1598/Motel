package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.User;
import com.example.intergration.motel.beans.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserServiceRepository extends JpaRepository<UserService, Integer> {

    @Query("select u from UserService u where u.iduser = ?1 and u.status = 1 order by u.timeEnd desc ")
    List<UserService> findUsingByIduser( int id );


    @Query("select u from UserService u where u.iduser = ?1 and u.status = 0 order by u.timeEnd desc ")
    List<UserService> findRegisterbyIduser( int id );

    @Query("select u from UserService u where u.status = 0")
    List<UserService> findAllByStatusIsFalse();



}
