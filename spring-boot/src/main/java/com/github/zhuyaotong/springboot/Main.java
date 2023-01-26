package com.github.zhuyaotong.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...");
    }
}

//@RestController
//class Hello {
//
//    @RequestMapping("/")
//    public String hi() {
//        return "你好呀！";
//    }
//}

@Configuration
class MyConfiguration {
    public MyConfiguration() {
        System.out.println("构造方法被调用");
    }

    @PostConstruct
    private void init() {
        System.out.println("PostConstruct注解方法被调用");
    }

    @PreDestroy
    private void shutdown() {
        System.out.println("PreDestroy注解方法被调用");
    }
}
