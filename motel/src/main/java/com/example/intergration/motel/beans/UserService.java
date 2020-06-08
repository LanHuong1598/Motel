package com.example.intergration.motel.beans;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "userservice")
public class UserService {
    @Id
    @Column(name = "iduserService")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idservice")
    private int idservice;

    @Column(name = "iduser")
    private int iduser;

    @Column(name = "timestart")
    private Date timeStart;

    @Column(name = "timeend")
    private Date timeEnd;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "status")
    private int status;


    public UserService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

}
