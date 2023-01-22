package com.example.unicvproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.unicvproject.service.SearchService;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class UniCVprojectApplication implements CommandLineRunner {
    @Autowired
    private SearchService service;
    public static void main(String[] args) {
        SpringApplication.run(UniCVprojectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nEnter command");
            String command = scanner.nextLine();
            service.parseCommand(command);
        }
    }
}
