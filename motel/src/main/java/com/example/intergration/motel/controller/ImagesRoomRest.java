package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.ImagesRoom;
import com.example.intergration.motel.beans.Room;
import com.example.intergration.motel.implementation.ImagesImpl;
import com.example.intergration.motel.repository.ImagesRoomRepository;
import com.example.intergration.motel.service.ImagesRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/room")
public class ImagesRoomRest {
    @Autowired
    ImagesRoomRepository images ;


    //Hiển thị phòng theo id
    @GetMapping("/{id}/images")
//    @ApiOperation(value = "Hiển thị phòng theo id")
    public ResponseEntity<List<ImagesRoom>> getImagesByIdRoom(
            @PathVariable(name = "id") int id
    ){
        return ResponseEntity.ok(
                images.findByidroom(id)
        );
    }
}
