package ru.teamandersen.controller;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.controller 
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/plus/{id}")
    public void incPoint(@PathVariable Long id){
        studentService.setPoint(id, 1);
    }
    @PostMapping("/minus/{id}")
    public void decPoint(@PathVariable Long id){
        studentService.setPoint(id, -1);
    }

    @GetMapping("/play")
    public ResponseEntity<List<Student>> getTwoStudentsFromDifferentTeam() {
        return ResponseEntity.ok(studentService.getTwoStudentsFromDifferentTeam());
    }

    @DeleteMapping("")
    public ResponseEntity clearAll() {
        studentService.clearAll();
        return ResponseEntity.noContent().build();
    }
}
