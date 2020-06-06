package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
    Optional<UserGroup> findByIdgroupuser( int id);

    List<UserGroup> findByName( String name );
}
