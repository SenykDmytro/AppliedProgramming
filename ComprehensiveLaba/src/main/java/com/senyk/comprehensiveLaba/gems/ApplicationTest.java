package com.senyk.comprehensiveLaba.gems;

import com.senyk.comprehensiveLaba.gems.app.ConsolApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
        ConsolApp consolApp= new ConsolApp();
    }
}
