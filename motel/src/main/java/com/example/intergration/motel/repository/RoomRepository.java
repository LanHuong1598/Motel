package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.News;
import com.example.intergration.motel.beans.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByIdroom(Integer idroom);

    @Query("select r from Room r where r.name like concat('%', ?1, '%')")
    List<Room> findByName(String name);

    @Query("select r from Room r where r.address like concat('%', ?1, '%')")
    List<Room> findByAddress( String address );

    @Query("select r from Room r where r.money < ?1 ")
    List<Room> findByMoney( String money );

    @Query("select r from Room r where r.numOfPeople < ?1 ")
    List<Room> findByNumOfPeople( String numOfPeople );

    @Query("select r from Room r where r.area < ?1 ")
    List<Room> findByArea( float area );

    List<Room> findByOwner(int id);

    Optional<Room> findByOwnerAndIdroom( int owner, int id );


    @Query("select n from Room n ORDER BY n.status DESC ")
    List<Room> getALll();
}
