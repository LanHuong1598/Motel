package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.Comment;
import com.example.intergration.motel.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    CommentRepository commentRepository;

    //Thêm comment
    @PostMapping("/{studentid}/news/{newsid}/comment")
    public ResponseEntity<Comment> addComment(
            @PathVariable(name = "studentid") int studentid,
            @PathVariable(name = "newsid") int newsid,
            @RequestBody Comment comment
    ){
        try{
            return new ResponseEntity<>(
                    commentRepository.save(new Comment(
                            comment.getTime(),
                            comment.getContext(),
                            studentid,
                            newsid
                    )), HttpStatus.OK
            );
        }
        catch ( Exception e ){
            return new ResponseEntity<>( HttpStatus.EXPECTATION_FAILED );
        }
    }
}
