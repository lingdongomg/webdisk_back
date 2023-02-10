package com.hukaichao.webdisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebdiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebdiskApplication.class, args);
    }

}
