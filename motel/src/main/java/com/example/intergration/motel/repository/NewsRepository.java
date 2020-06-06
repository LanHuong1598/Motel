package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Optional<News> findByIdnews( int id );

    List<News> findByIdroom( int id );

    List<News> findByIduser( int id );

    @Query("select n from News n where n.timestart > ?1 and n.iduser = ?2")
    List<News> findByTimestartAndIduser(Date date, int id);

    @Query("select n from News n where n.timestart > ?1 and n.idroom = ?2")
    List<News> findByTimestartAndIdroom(Date date, int roomid);

    News deleteByIdnews( int id);
}
