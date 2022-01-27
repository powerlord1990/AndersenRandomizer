package ru.teamandersen.component;
/* 
24.01.2022: Alexey created this file inside the package: ru.teamandersen.component 
*/

import org.springframework.stereotype.Component;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecureRandomGetStudents {
    private final StudentRepository studentRepository;

    private boolean isFirst = true;
    private final SecureRandom random = new SecureRandom();
    private Student prev;
    private Student last;

    public SecureRandomGetStudents(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student[] getStudents() {
        Student toAsk;
        Student toPoll;
        if (isFirst) {
            toAsk = getRandomMove(studentRepository.findAll());
            last = toAsk;
            isFirst = false;
        } else toAsk = prev;
        toAsk.setIsAsked(true);
        toPoll = getRandomMove(getStudentToPollFilter(studentRepository.findStudentByIsPolledIsFalse(), toAsk));
        toPoll.setIsPolled(true);
        if(!toAsk.equals(toPoll)){
            studentRepository.save(toAsk);
            studentRepository.save(toPoll);
        }
        prev = toPoll;
        return new Student[]{toAsk, toPoll}; // must return student which ask and student which poll
    }

    private List<Student> getStudentToPollFilter(List<Student> students, Student toAsk) {
        return students.stream().filter(x -> !x.getIsPolled()
                && !(x.getTeamId() == toAsk.getTeamId())
                && x.getId() != last.getId()).collect(Collectors.toList());
    }

    private Student getRandomMove(List<Student> students) {
        if (students.isEmpty()) {
            return last;
        }
        return students.get(random.nextInt(students.size()));
    }
}
