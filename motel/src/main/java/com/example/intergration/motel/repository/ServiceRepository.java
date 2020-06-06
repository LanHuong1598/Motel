package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Optional<Service> findByIdservice( int id );
}
