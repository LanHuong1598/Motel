package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.ImagesRoom;
import com.example.intergration.motel.beans.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRoomRepository extends JpaRepository<ImagesRoom, Integer> {

    List<ImagesRoom> findByidroom(int id);
}
