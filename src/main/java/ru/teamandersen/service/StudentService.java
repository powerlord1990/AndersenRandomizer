package ru.teamandersen.service;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.service 
*/

import org.springframework.stereotype.Service;
import ru.teamandersen.dtos.StudentResponseDto;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

//TODO добавить метод, который должен возвращать текущее состояние опроса и обновлять его при вызове опроса "/play"
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final RandomGetStudents randomGetStudents;

    public StudentService(StudentRepository studentRepository, RandomGetStudents randomGetStudents) {
        this.studentRepository = studentRepository;
        this.randomGetStudents = randomGetStudents;
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
        ExcelService excel = new ExcelService();
        if (path.equals("")) {
            return;
        }
        studentRepository.saveAll(excel.readExcel(path));
    }

    public List<StudentResponseDto> getTwoStudentsFromDifferentTeam() {
        Student[] students = randomGetStudents.getStudents();
        List<StudentResponseDto> studentRequestBodyDto = new ArrayList<>();
        if (students.length == 0) return Collections.emptyList();
        Arrays.stream(students).forEach(std -> studentRequestBodyDto.add(new StudentResponseDto(std)));
        return studentRequestBodyDto;
    }

    public void setPoint(Long id, double point) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student stud = student.get();
            stud.setScore(stud.getScore().add(BigDecimal.valueOf(point)));
            studentRepository.save(stud);
        }
    }

    public void restartQuiz() {
        List<Student> students = studentRepository.findAll();
        students.forEach(this::setFalseForAllQuiz);
        studentRepository.saveAll(students);
    }

    private Student setFalseForAllQuiz(Student student) {
        student.setIsPolled(false);
        student.setIsAsked(false);
        return student;
    }

    public void clearAll() {
        studentRepository.deleteAll();
        randomGetStudents.clearQueue();
    }
}
