package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.*;
import com.example.intergration.motel.implementation.NewsImplementation;
import com.example.intergration.motel.implementation.RoomImplementation;
import com.example.intergration.motel.implementation.UserImplementation;
import com.example.intergration.motel.repository.CommentRepository;
import com.example.intergration.motel.repository.ServiceRepository;
import com.example.intergration.motel.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/host")
public class HostController {
    @Autowired
    NewsImplementation newsImplementation;

    @Autowired
    UserImplementation userImplementation;

    @Autowired
    RoomImplementation roomImplementation;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    UserServiceRepository userServiceRepository;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews(
            @PathVariable(name = "hostid") int id
    ){
        String group = userImplementation.getUserGroup(id);
        if( Integer.parseInt(group) != 3 ){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(
                    newsImplementation.getNewsByUser(id)
            );
        }
    }

    //Lấy tất cả bài đăng của chủ trọ
    @GetMapping("/{hostid}/news")
    public ResponseEntity<List<News>> getAllNewswithhost(
            @PathVariable(name = "hostid") int id
    ){
        String group = userImplementation.getUserGroup(id);
        if( Integer.parseInt(group) != 3 ){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(
                    newsImplementation.getNewsByUser(id)
            );
        }
    }

    //Tìm kiếm bài đăng theo ngày
    @GetMapping("/{hostid}/news/date/{date}")
    public ResponseEntity<List<News>> getNewsByDate(
            @PathVariable(name = "hostid") int id,
            @PathVariable(name = "date")Date date
            ){
        String group = userImplementation.getUserGroup(id);
        if( Integer.parseInt(group) != 3){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(
                    newsImplementation.getNewsByDateAndUser(date,id)
            );
        }
    }

    //Hiển thị tất cả phòng của chủ trọ
    @GetMapping("/{hostid}/rooms")
    public ResponseEntity<List<Room>> getAllRoom(
            @PathVariable(name = "hostid") int id
    ){
        String group = userImplementation.getUserGroup(id);
        if( Integer.parseInt(group) != 3 ){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(
                    roomImplementation.findAllHostRooms(id)
            );
        }
    }

    //Hiển thị phòng của chủ trọ theo id
    @GetMapping("/{hostid}/rooms/{roomid}")
    public ResponseEntity<Room> getRoomById(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "roomid") int roomid
    ){
        String group = userImplementation.getUserGroup(hostid);
        if( Integer.parseInt(group) != 3 ){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(
                    roomImplementation.findRoomByOwnerAndId(hostid, roomid)
            );
        }
    }

    //Hienr thị tất cả tin bài của phòng
    @GetMapping("/{hostid}/rooms/{roomid}/news")
    public ResponseEntity<List<News>> getAllRoomNews(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "roomid") int roomid
    ){
        String group = userImplementation.getUserGroup(hostid);
        if( Integer.parseInt(group) != 3 ){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(
                    newsImplementation.getNewsByRoom(roomid)
            );
        }
    }

    //Thêm mói tin bài
    @PostMapping("/{hostid}/rooms/{roomid}/news")
    public ResponseEntity<News> createNews(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "roomid") int roomid,
            @RequestBody News news
            ){
        try{
            return new ResponseEntity<>(
                    newsImplementation.saveNews( new News(
                            roomid,
                            news.getStatus(),
                            news.getTimestart(),
                            news.getTimeend(),
                            news.getContext(),
                            news.getType(),
                            hostid
                    )),
                    HttpStatus.CREATED
            );
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Chỉnh sửa tin bài, chỉ có người đăng mới được chỉnh sửa
    @PutMapping("/{hostid}/news/{newsid}")
    public ResponseEntity<News> editNews(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "newsid") int newsid,
            @RequestBody News news
    ){
        News newsData = newsImplementation.getNewsById(newsid);
        if ( newsData == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if( newsData.getIduser() != hostid ){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            newsData.setStatus( news.getStatus() );
            newsData.setTimestart( news.getTimestart() );
            newsData.setTimeend(news.getTimeend());
            newsData.setContext(news.getContext());
            newsData.setType(news.getType());
            return new ResponseEntity<>(
                    newsData,
                    HttpStatus.OK
            );
        }
    }

    //Xóa tin bài, chỉ người đăng mới được xóa
    @DeleteMapping("/{hostid}/news/{newsid}")
    public ResponseEntity<News> deleteNews(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "newsid") int newsid
    ){
        News newsData = newsImplementation.getNewsById(newsid);
        if ( newsData == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if( newsData.getIduser() != hostid ){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            try{
                newsImplementation.deleteNewsById(hostid);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            catch( Exception e ){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    //Thêm comment mới
    @PostMapping("/{hostid}/news/{newsid}/comment")
    public ResponseEntity<Comment> addComment(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "newsid") int newsid,
            @RequestBody Comment comment
    ){
        try{
            return new ResponseEntity<>(
                    commentRepository.save(new Comment(
                            comment.getTime(),
                            comment.getContext(),
                            hostid,
                            newsid
                    )), HttpStatus.OK
            );
        }
        catch ( Exception e ){
            return new ResponseEntity<>( HttpStatus.EXPECTATION_FAILED );
        }
    }

    //Hiển thị tất cả dịch vụ đã đăng kí
    @GetMapping("/{hostid}/userservices/register")
    public ResponseEntity<UserService> getRegister(
            @PathVariable(name = "hostid") int hostid
    ){
        List<UserService> userServiceList = userServiceRepository.findRegisterbyIduser(hostid);
        if (userServiceList != null)
        return ResponseEntity.ok(
                userServiceList.get(0)
        );
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    //Hiển thị tất cả dịch vụ đã đăng kí
    @GetMapping("/{hostid}/userservices/using")
    public ResponseEntity<UserService> getUsingServicebyIduserWithHost(
            @PathVariable(name = "hostid") int hostid
    ){
        List<UserService> userServiceList = userServiceRepository.findUsingByIduser(hostid);
        java.util.Date date =  new java.util.Date( );
        Date date1 = new Date(date.getTime()) ;
        if (userServiceList.get(0).getTimeEnd().compareTo(date1) >= 0)
        return ResponseEntity.ok(
                userServiceList.get(0)
        );
        else     return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    //Hiển thị tất cả dịch vụ
    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices(){
        return ResponseEntity.ok(
                serviceRepository.findAll()
        );
    }

    //Đăng kí dịch vụ
    @PostMapping("/{hostid}/user_service/{serviceid}")
    public ResponseEntity<UserService> registerService(
            @PathVariable(name = "hostid") int hostid,
            @PathVariable(name = "serviceid") int serviceid
    ){
        Optional<Service> _service = serviceRepository.findByIdservice(serviceid);
        if (!_service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            UserService us = new UserService();
            us.setIdservice( serviceid );
            us.setIduser(hostid);
            Calendar c1 = Calendar.getInstance() ;
            java.util.Date date = c1.getTime();
            c1.roll(Calendar.DATE, 30);
            java.util.Date dateend = c1.getTime() ;
            Date date1 = new Date(date.getTime()) ;
            Date date2 = new Date(dateend.getTime()) ;
            us.setTimeStart(date1);
            us.setTimeEnd(date2);
            us.setStatus(0);
            return new ResponseEntity<>(
                    userServiceRepository.save(us), HttpStatus.OK
            );
        }
    }
}
