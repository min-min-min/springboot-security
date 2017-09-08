package com.chenjing.springbootauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAuthApplication.class, args);
    }
}
