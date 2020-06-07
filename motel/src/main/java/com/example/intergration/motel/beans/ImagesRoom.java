package com.example.intergration.motel.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "imagesroom")
public class ImagesRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimagesroom")
    private int idimagesRoom;

    @Column(name = "idroom")
    private Integer idroom;

    @Column(name = "url")
    private String url;

    public int getIdimagesRoom() {
        return idimagesRoom;
    }

    public void setIdimagesRoom(int idimagesRoom) {
        this.idimagesRoom = idimagesRoom;
    }

    public Integer getIdroom() {
        return idroom;
    }

    public void setIdroom(Integer idroom) {
        this.idroom = idroom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

