package com.example.intergration.motel.service;

import com.example.intergration.motel.beans.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room findRoomById( int id );

    List<Room> findAllRoom();

    List<Room> findRoomByAddress( String address);

    List<Room> findRoomByPrice( float price );

    List<Room> findRoomByNumberOfPeople( int num );

    List<Room> findRoomByArea( float area );

    List<Room> findAllHostRooms( int id );

    Room findRoomByOwnerAndId( int owner, int id );
}
