package com.example.intergration.motel.service;

import com.example.intergration.motel.beans.News;

import java.sql.Date;
import java.util.List;

public interface NewsService {
    News getNewsById( int id );

    List<News> getNewsByUser( int userId );

    List<News> getNewsByRoom( int roomId );

    News saveNews( News news );

    List<News> getNewsByDateAndUser(Date date, int id);

    List<News> getNewsByDateAndRoom( Date date, int roomid);

    News save( News news );

    News deleteNewsById(int id );

    List<News> getAll(Date date) ;


}
