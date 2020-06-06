package com.example.intergration.motel.beans;

import javax.persistence.*;

@Entity
@Table(name = "groupuser")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgroupuser")
    private int idgroupuser;

    @Column(name = "name")
    private String name;

    public UserGroup() {
    }

    public int getIdgroupuser() {
        return idgroupuser;
    }

    public void setIdgroupuser(int idgroupuser) {
        this.idgroupuser = idgroupuser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
