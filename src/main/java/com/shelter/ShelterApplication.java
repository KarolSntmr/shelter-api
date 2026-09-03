package com.shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShelterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShelterApplication.class, args);
        System.out.println("✅ API del shelter corriendo en http://localhost:8080");
    }
}
