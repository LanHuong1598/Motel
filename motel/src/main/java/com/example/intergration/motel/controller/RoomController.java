package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.Comment;
import com.example.intergration.motel.beans.News;
import com.example.intergration.motel.beans.Room;
import com.example.intergration.motel.implementation.RoomImplementation;
import com.example.intergration.motel.repository.CommentRepository;
import com.example.intergration.motel.repository.NewsRepository;
import com.example.intergration.motel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    RoomImplementation roomImplementation;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CommentRepository commentRepository;

    //Hiển thị tất cả các phòng
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRoom(){
        return ResponseEntity.ok(
                roomImplementation.findAllRoom()
        );
    }

    //Hiển thị phòng theo id
    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(
            @PathVariable(name = "id") int id
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomById(id)
        );
    }

    //Tìm kiếm phòng theo địa chỉ
    @GetMapping("/address/{address}")
    public ResponseEntity<List<Room>> getRoomByAddress(
            @PathVariable(name = "address") String address
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByAddress(address)
        );
    }

    //Tìm kiếm phòng theo giá cả
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Room>> getRoomByPrice(
            @PathVariable(name = "price") float price
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByPrice(price)
        );
    }

    //Tìm kiếm phòng theo số người
    @GetMapping("/number/{num}")
    public ResponseEntity<List<Room>> getRoomByNumberOfPeople(
            @PathVariable(name = "num") int num
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByNumberOfPeople(num)
        );
    }

    //Tìm kiếm phòng theo dienj tích
    @GetMapping("/area/{area}")
    public ResponseEntity<List<Room>> getRoomByArea(
            @PathVariable(name = "area") float area
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByArea(area)
        );
    }

    //Hiển thị các bài đăng của phòng
    @GetMapping("/rooms/{roomid}/news")
    public ResponseEntity<List<News>> getAllNewsOfRoom(
            @PathVariable(name = "roomid") int roomid
    ){
        return ResponseEntity.ok(
                newsRepository.findByIdroom(roomid)
        );
    }

    //Hiển thị các comment của bài đăng
    @GetMapping("/news/{newsid}/comments")
    public ResponseEntity<List<Comment>> getAllNewsComment(
            @PathVariable(name = "newsid") int newsid
    ){
        return ResponseEntity.ok(
                commentRepository.findByIdnew(newsid)
        );
    }
}
