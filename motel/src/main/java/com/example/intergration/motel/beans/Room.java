package com.example.intergration.motel.beans;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table( name = "room")
public class Room {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "idroom")
    private int idroom;

    @Column( name = "name")
    private String name;

    @Column( name = "address")
    private String address;

    @Column( name = "money")
    private String money;

    @Column( name = "numofpeople")
    private String numOfPeople;

    @Column( name = "area")
    private float area;

    @Column( name = "note")
    private String note;

    @Column( name = "status")
    private String status;

    @Column( name = "owner")
    private int owner;

    public Room() {
    }

    public int getIdroom() {
        return idroom;
    }

    public void setIdroom(int idroom) {
        this.idroom = idroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(String numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
