package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByAcc( String acc );

    Optional<User> findByIduser( int iduser );

    List<User> findAllByIdgroup(String idgroup);

    @Query("select u from User u where u.acc like concat('%', ?1, '%')")
    List<User> findAllByAcc( String username );
}
