package com.example.intergration.motel.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class NewsGet {

    private String name;

    private String address;

    private String money;

    private int status;

}
