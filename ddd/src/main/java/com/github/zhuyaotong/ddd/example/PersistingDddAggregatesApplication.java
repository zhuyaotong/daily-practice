package com.github.zhuyaotong.ddd.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.zhuyaotong.ddd.example.order")
public class PersistingDddAggregatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersistingDddAggregatesApplication.class, args);
    }
}
