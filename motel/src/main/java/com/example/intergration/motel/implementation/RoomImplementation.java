package com.example.intergration.motel.implementation;

import com.example.intergration.motel.beans.Room;
import com.example.intergration.motel.repository.RoomRepository;
import com.example.intergration.motel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomImplementation implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room findRoomById(int id) {
        Optional<Room> room = roomRepository.findByIdroom(id);
        return room.orElse(null);
    }

    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findRoomByAddress(String address) {
        return roomRepository.findByAddress( address );
    }

    @Override
    public List<Room> findRoomByPrice(float price) {
        return roomRepository
                .findByMoney( String.valueOf(price));
    }

    @Override
    public List<Room> findRoomByNumberOfPeople(int num) {
        return roomRepository.findByNumOfPeople(
                String.valueOf(num)
        );
    }

    @Override
    public List<Room> findRoomByArea(float area) {
        return roomRepository.findByArea(area);
    }

    @Override
    public List<Room> findAllHostRooms(int id) {
        return roomRepository.findByOwner(id);
    }

    @Override
    public Room findRoomByOwnerAndId(int owner, int id) {
        Optional<Room> room = roomRepository.findByOwnerAndIdroom(owner, id);

        return room.orElse(null);
    }
}
