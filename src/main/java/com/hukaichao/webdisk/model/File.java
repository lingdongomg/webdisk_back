package com.hukaichao.webdisk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class File implements Serializable {
    @Getter @Setter @Id @GeneratedValue
    private int id;
    @Getter @Setter
    private String path;
    @Getter @Setter
    private String fileName;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String size;
    @Getter @Setter
    private String uploadTime;
    @Getter @Setter
    private Boolean isDelete;


    public File() {}

    public File(String path,String fileName, String type, String size, String uploadTime) {
        this.path = path;
        this.fileName = fileName;
        this.type = type;
        this.size = size;
        this.uploadTime = uploadTime;
        this.isDelete = false;
    }

}
