package com.alex.keeper;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TreeMap;

@SpringBootApplication
public class KeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeeperApplication.class, args);
    }

}
