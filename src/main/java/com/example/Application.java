package com.example;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.example.mapper")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.warn("软件工程经济学线上实验系统");
        log.warn("项目成功启动后用Chrome访问：http://localhost:9999/page/end/login.html");
        log.warn("账号：admin   密码：admin");
    }
}
