package com.hukaichao.webdisk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Getter @Setter @Id private String username;
    @Getter @Setter private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
