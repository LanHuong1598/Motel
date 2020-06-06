package com.example.intergration.motel.beans;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtProcessing;
import java.sql.Date;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnews")
    private int idnews;

    @Column(name = "idroom")
    private int idroom;

    @Column(name = "status")
    private int status;

    @Column(name = "timestart")
    private Date timestart;

    @Column(name = "timeend")
    private Date timeend;

    @Column(name = "context")
    private String context;

    @Column(name = "type")
    private int type;

    @Column(name = "iduser")
    private int iduser;

    public News() {
    }

    public News(
            int idroom,
            int status,
            Date timestart,
            Date timeend,
            String context,
            int type,
            int iduser
    ){
        this.idroom = idroom;
        this.status = status;
        this.timestart = timestart;
        this.timeend = timeend;
        this.context = context;
        this.type = type;
        this.iduser = iduser;
    }

    public int getIdnews() {
        return idnews;
    }

    public void setIdnews(int idnews) {
        this.idnews = idnews;
    }

    public int getIdroom() {
        return idroom;
    }

    public void setIdroom(int idroom) {
        this.idroom = idroom;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestart() {
        return timestart;
    }

    public void setTimestart(Date timestart) {
        this.timestart = timestart;
    }

    public Date getTimeend() {
        return timeend;
    }

    public void setTimeend(Date timeend) {
        this.timeend = timeend;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
