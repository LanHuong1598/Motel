package com.example.intergration.motel.beans;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int iduser;

    @Column(name = "acc")
    private String acc;
    @Column(name = "password")
    private String password;
    @Column(name = "idgroup")
    private String idgroup;

    public User() {
    }

    public User( String acc, String password, String idgroup){
        this.acc = acc;
        this.password = password;
        this.idgroup = idgroup;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }
}
