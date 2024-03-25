package com.tka4nik.nexign_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NexignTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexignTaskApplication.class, args);
        CDRService cdr = new CDRService();
        cdr.generate_cdr();
    }

}
