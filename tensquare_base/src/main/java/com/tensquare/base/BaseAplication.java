package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author hanshuiqiu
 * @create 2020-09-23 19:50
 */

@SpringBootApplication
public class BaseAplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseAplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

}
