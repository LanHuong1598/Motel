package com.example.intergration.motel.implementation;

import com.example.intergration.motel.beans.News;
import com.example.intergration.motel.beans.NewsGet;
import com.example.intergration.motel.repository.NewsRepository;
import com.example.intergration.motel.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsImplementation implements NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Override
    public News getNewsById(int id) {
        Optional<News> news = newsRepository.findByIdnews(id);
        return news.orElse(null);
    }

    @Override
    public List<News> getNewsByUser(int userId) {
        return newsRepository.findByIduser( userId );
    }

    @Override
    public List<News> getNewsByRoom(int roomId) {
        return newsRepository.findByIdroom(roomId);
    }

    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> getNewsByDateAndUser(Date date, int id) {
        return newsRepository.findByTimestartAndIduser(date, id);
    }

    @Override
    public List<News> getNewsByDateAndRoom(Date date, int roomid) {
        return newsRepository.findByTimestartAndIdroom(date, roomid);
    }


    @Override
    public List<News> getAll(Date date) {
        return newsRepository.getALll(date);
    }


    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News deleteNewsById(int id) {
        return newsRepository.deleteByIdnews(id);
    }


}
