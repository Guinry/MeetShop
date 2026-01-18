package com.meetshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.meetshop.mapper")
@SpringBootApplication
public class MeetShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetShopApplication.class, args);
    }

}
