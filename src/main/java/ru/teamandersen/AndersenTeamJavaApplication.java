package ru.teamandersen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.teamandersen.entity.Student;
import ru.teamandersen.service.WorkWithExel;

import java.util.ArrayList;
import java.util.List;



@SpringBootApplication
public class AndersenTeamJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AndersenTeamJavaApplication.class, args);
    }
}
