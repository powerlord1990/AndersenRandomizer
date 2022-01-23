package ru.teamandersen.controller;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.controller 
*/

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamandersen.dtos.StudentRequestBodyDto;
import ru.teamandersen.entity.Student;
import ru.teamandersen.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentsController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping("")
    public List<Student> addStudent(@RequestBody String text){
        return studentService.addNewStudents(text);
    }

    @GetMapping("/play")
    public List<StudentRequestBodyDto> getTwoStudentsFromDifferentTeam(){
        return studentService.getTwoStudentsFromDifferentTeam();
    }
}
