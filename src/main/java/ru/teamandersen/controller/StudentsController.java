package ru.teamandersen.controller;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.controller 
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.teamandersen.dtos.StudentResponseDto;
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
    public void incPoint(@PathVariable Long id, @RequestBody Double score) {
        studentService.setPoint(id, score);
    }

    @PostMapping("/minus/{id}")
    public void decPoint(@PathVariable Long id, @RequestBody Double score) {
        studentService.setPoint(id, score);
    }

    @PostMapping("/import")
    public ResponseEntity decPoint(@RequestBody String path) {
        studentService.addNewStudentsWithExcel(path);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity restartQuiz(){
        studentService.restartQuiz();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/play")
    public ResponseEntity<List<StudentResponseDto>> getTwoStudentsFromDifferentTeam() {
        return ResponseEntity.ok(studentService.getTwoStudentsFromDifferentTeam());
    }

    @DeleteMapping("")
    public ResponseEntity clearAll() {
        studentService.clearAll();
        return ResponseEntity.noContent().build();
    }
}
