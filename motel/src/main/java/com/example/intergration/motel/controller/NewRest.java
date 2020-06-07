package com.example.intergration.motel.controller;


import com.example.intergration.motel.beans.News;
import com.example.intergration.motel.beans.NewsGet;
import com.example.intergration.motel.beans.Room;
import com.example.intergration.motel.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.enterprise.inject.New;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/news")

public class NewRest {
    @Autowired
    NewsRepository newsRepository;
    @GetMapping("")
    public ResponseEntity<List<News>> getAllRoom(){
        java.util.Date date = new java.util.Date() ;
        Date date1 = new Date(date.getTime()) ;
        return ResponseEntity.ok(
                 newsRepository.getALll(date1));

    }
}
