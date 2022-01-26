package ru.teamandersen.service;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.service 
*/

import org.springframework.stereotype.Service;
import ru.teamandersen.component.SecureRandomGetStudents;
import ru.teamandersen.dtos.StudentRequestBodyDto;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final SecureRandomGetStudents secureRandomGetStudents;

    public StudentService(StudentRepository studentRepository, SecureRandomGetStudents secureRandomGetStudents) {
        this.studentRepository = studentRepository;
        this.secureRandomGetStudents = secureRandomGetStudents;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentRequestBodyDto studentDto) {
        return studentRepository.save(new Student.Builder(studentDto.getTeamId(), studentDto.getFirstname(), studentDto.getSecondname()).build());
    }

    public void addNewStudents(String text) {
        String[] strings = text.split("\n");
        for (String s : strings) {
            String[] words = s.split(" ");
            studentRepository.save(new Student.Builder(Long.parseLong(words[0]), words[1], words[2]).build());
        }
    }

    public List<StudentRequestBodyDto> getTwoStudentsFromDifferentTeam() {
        Student[] students = secureRandomGetStudents.getStudents();
        List<StudentRequestBodyDto> studentRequestList = new ArrayList<>();
        for (Student s : students) {
            studentRequestList.add(new StudentRequestBodyDto(s));
        }
        if (studentRequestList.get(0).getTeamId() == studentRequestList.get(1).getTeamId())
            return Collections.emptyList();
        return studentRequestList;
    }

    public void clearAll() {
        studentRepository.deleteAll();
    }

}
