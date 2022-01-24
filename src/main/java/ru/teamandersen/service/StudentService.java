package ru.teamandersen.service;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.service 
*/

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamandersen.dtos.StudentRequestBodyDto;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentRequestBodyDto studentDto) {
        return studentRepository.save(new Student.Builder(studentDto.getTeamId(), studentDto.getFirstname(), studentDto.getSecondname()).build());
    }

    public List<Student> addNewStudents(String text) {
        //TODO распарсить text
        String[] words = text.split(" ");
        Student student = new Student.Builder(Long.parseLong(words[0]), words[1], words[2]).build();

        return Collections.EMPTY_LIST;
    }
}
