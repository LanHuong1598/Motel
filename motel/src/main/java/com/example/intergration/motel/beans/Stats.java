package com.example.intergration.motel.beans;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "stats")
public class Stats {


    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdstats() {
        return idstats;
    }

    public void setIdstats(int idstats) {
        this.idstats = idstats;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstats")
    private int idstats;

    @Column(name = "idservice")
    private int idservice ;


    @Column(name = "date")
    private Date date ;

    @Column(name = "number")
    private int number ;


}
