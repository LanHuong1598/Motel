package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.Comment;
import com.example.intergration.motel.beans.News;
import com.example.intergration.motel.beans.Room;
import com.example.intergration.motel.implementation.RoomImplementation;
import com.example.intergration.motel.repository.CommentRepository;
import com.example.intergration.motel.repository.NewsRepository;
import com.example.intergration.motel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")

//@Api(value = "Room - api ", tags = "room-api")
public class RoomController {
    @Autowired
    RoomImplementation roomImplementation;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CommentRepository commentRepository;

    //

//    @ApiOperation(value = "Hiển thị tất cả các phòng")
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRoom(){
        return ResponseEntity.ok(
                roomImplementation.findAllRoom()
        );
    }

    //Hiển thị phòng theo id
    @GetMapping("/rooms/{id}")
//    @ApiOperation(value = "Hiển thị phòng theo id")
    public ResponseEntity<Room> getRoomById(
            @PathVariable(name = "id") int id
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomById(id)
        );
    }

    //Tìm kiếm phòng theo địa chỉ
    @GetMapping("/address/{address}")
//    @ApiOperation(value = "Hiển thị tất cả các phòng")
    public ResponseEntity<List<Room>> getRoomByAddress(
            @PathVariable(name = "address") String address
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByAddress(address)
        );
    }

    //Tìm kiếm phòng theo giá cả
//    @ApiOperation(value = "Hiển thị tất cả các phòng")
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Room>> getRoomByPrice(
            @PathVariable(name = "price") float price
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByPrice(price)
        );
    }

    //Tìm kiếm phòng theo số người
//    @ApiOperation(value = "Hiển thị tất cả các phòng")
    @GetMapping("/number/{num}")
    public ResponseEntity<List<Room>> getRoomByNumberOfPeople(
            @PathVariable(name = "num") int num
    ){
        return ResponseEntity.ok(
                roomImplementation.findRoomByNumberOfPeople(num)
        );
    }

    //Tìm kiếm phòng theo dienj tích
//    @ApiOperation(value = "Hiển thị tất cả các phòng")
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
//    @ApiOperation(value = "Hiển thị tất cả các phòng")
    public ResponseEntity<List<News>> getAllNewsOfRoom(
            @PathVariable(name = "roomid") int roomid
    ){
        return ResponseEntity.ok(
                newsRepository.findByIdroom(roomid)
        );
    }

    //Hiển thị các comment của bài đăng
//    @ApiOperation(value = "Hiển thị tất cả các phòng")
    @GetMapping("/news/{newsid}/comments")
    public ResponseEntity<List<Comment>> getAllNewsComment(
            @PathVariable(name = "newsid") int newsid
    ){
        return ResponseEntity.ok(
                commentRepository.findByIdnew(newsid)
        );
    }

    //Thêm mói room
    @PostMapping(path = "/")
    public ResponseEntity<Room> createRoom(
            @RequestBody Room room
    ){
        try{
            return new ResponseEntity<>(
                    roomImplementation.createRoom( room),
                    HttpStatus.CREATED
            );
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Chỉnh sửa rooms
    @PutMapping("/{hostid}/rooms/{roomid}")
    public ResponseEntity<Room> editNews(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "roomid") int roomid,
            @RequestBody Room room
    ){
        Room newsData = roomImplementation.findRoomById(roomid);
        if ( newsData == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if( newsData.getOwner() != hostid ){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            newsData.setAddress(room.getAddress());
            newsData.setArea(room.getArea());
            newsData.setMoney(room.getMoney());
            newsData.setName(room.getName());
            newsData.setNote(room.getNote());
            newsData.setNumOfPeople(room.getNumOfPeople());
            newsData.setStatus(room.getStatus());
            newsData.setOwner(room.getOwner());

            roomImplementation.updateRoom(room) ;
            return new ResponseEntity<Room>(
                    room,
                    HttpStatus.OK
            );
        }
    }
//
    //Xóa tin bài, chỉ người đăng mới được xóa
    @DeleteMapping("/{hostid}/rooms/{roomid}")
    public ResponseEntity<Room> deleteNews(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "roomid") int roomid
    ){
        Room room = roomImplementation.findRoomById(roomid);
        if ( room == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if( room.getOwner() != hostid ){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            try{
                roomImplementation.deleteRoom(room);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            catch( Exception e ){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

}
