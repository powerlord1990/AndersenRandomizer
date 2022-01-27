package ru.teamandersen.service;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.service 
*/

import org.springframework.stereotype.Service;
import ru.teamandersen.component.SecureRandomGetStudents;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.ExcelStudentRepository;
import ru.teamandersen.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ExcelStudentRepository excelStudentRepository;

    private final SecureRandomGetStudents secureRandomGetStudents;

    public StudentService(StudentRepository studentRepository, ExcelStudentRepository excelStudentRepository, SecureRandomGetStudents secureRandomGetStudents) {
        this.studentRepository = studentRepository;
        this.excelStudentRepository = excelStudentRepository;
        this.secureRandomGetStudents = secureRandomGetStudents;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void addNewStudents(String text) {

        String[] strings = text.split("\n");
        for (String s : strings) {
            String[] words = s.split(" ");
            studentRepository.save(new Student.Builder(Long.parseLong(words[0]), words[1], words[2]).build());
        }
    }

    public void addNewStudentsWithExcel(String path) {
        WorkWithExel excel = new WorkWithExel();
        if (path.equals("")){
            return;
        }
        excel.readExel(path);
        excelStudentRepository.saveAll(excel.getStudents());
    }

    public List<Student> getTwoStudentsFromDifferentTeam() {
        Student[] students = secureRandomGetStudents.getStudents();
        if (students[0].equals(students[1])) return Collections.emptyList();
        return Arrays.stream(students).collect(Collectors.toList());
    }

    public void setPoint(Long id, int point) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student stud = student.get();
            stud.setScore(stud.getScore() + point);
            studentRepository.save(stud);
        }
    }

    public void clearAll() {
        studentRepository.deleteAll();
    }

}
