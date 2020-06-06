package com.example.intergration.motel.beans;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table( name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomment")
    private int idcomment;

    @Column(name = "time")
    private Date time;

    @Column(name = "context")
    private String context;

    @Column(name = "iduser")
    private int iduser;

    @Column(name = "idnew")
    private int idnew;

    public Comment() {
    }

    public Comment(
            Date time,
            String context,
            int iduser,
            int idnew
    ){
        this.time = time;
        this.context = context;
        this.iduser = iduser;
        this.idnew = idnew;
    }

    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdnew() {
        return idnew;
    }

    public void setIdnew(int idnew) {
        this.idnew = idnew;
    }
}
