package ru.teamandersen.service;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.service 
*/

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamandersen.component.SecureRandomGetStudents;
import ru.teamandersen.dtos.StudentRequestBodyDto;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    SecureRandomGetStudents secureRandomGetStudents;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentRequestBodyDto studentDto) {
        return studentRepository.save(new Student.Builder(studentDto.getTeamId(), studentDto.getFirstname(),studentDto.getSecondname()).build());
    }

    public List<Student> addNewStudents(String text) {
        //TODO
        return Collections.emptyList();
    }

    public List<StudentRequestBodyDto> getTwoStudentsFromDifferentTeam(){
        Student[] students = secureRandomGetStudents.getStudents();
        List<StudentRequestBodyDto> studentRequestList = new ArrayList<>();
        for (Student s: students){
            studentRequestList.add(new StudentRequestBodyDto(s));
        }
        if (studentRequestList.get(0).getTeamId() == studentRequestList.get(1).getTeamId()) return Collections.emptyList();
        return studentRequestList;
    }


}
