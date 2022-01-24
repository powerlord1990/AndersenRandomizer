package ru.teamandersen.controller;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.controller 
*/

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.teamandersen.dtos.StudentRequestBodyDto;
import ru.teamandersen.entity.Student;
import ru.teamandersen.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {
    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping("")
    public void addStudent(@RequestBody String text) {
        studentService.addNewStudents(text);
    }

    @GetMapping("/play")
    public ResponseEntity<List<StudentRequestBodyDto>> getTwoStudentsFromDifferentTeam() {
        return ResponseEntity.ok(studentService.getTwoStudentsFromDifferentTeam());
    }

    @DeleteMapping("")
    public ResponseEntity clearAll() {
        studentService.clearAll();
        return ResponseEntity.noContent().build();
    }
}
