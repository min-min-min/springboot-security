package com.chenjing.springbootauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAuthApplication.class, args);
    }
}
