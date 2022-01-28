package ru.teamandersen.component;
/* 
24.01.2022: Alexey created this file inside the package: ru.teamandersen.component 
*/

import org.springframework.stereotype.Component;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RandomGetStudents {
    private final StudentRepository studentRepository;
    List<Student> studentPoll = new ArrayList<>();
    List<Student> studentAsk = new ArrayList<>();

    private boolean isFirst = true;
    private final Random random = new Random();
    private Student prev;
    private Student last;

    public RandomGetStudents(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student[] getStudents() {
        studentAsk = studentRepository.findStudentByIsAskedIsFalse();
        studentPoll = studentRepository.findStudentsByIsPolledIsFalse();
        check(studentAsk, studentPoll);
        Student ask;

        if (isFirst) {
            ask = getRandomMove(studentAsk);
            last = ask;
            isFirst = false;
        } else ask = prev;

        Student poll = getRandomMove(getStudentToPollFilter(studentPoll, last));
        if (!studentRepository.findAll().isEmpty() && ask != poll) {
            ask.setIsAsked(true);
            poll.setIsPolled(true);
            prev = poll;
            studentRepository.save(ask);
            studentRepository.save(poll);
            return new Student[]{ask, poll};
        }
        return new Student[]{};
    }

    private void check(List<Student> a, List<Student> b){
        isFirst = a.equals(b);
    }

    private List<Student> getStudentToPollFilter(List<Student> students, Student toAsk) {
        for (Student s : students) {
            if (!toAsk.getTeamId().equals(s.getTeamId())) {
                return students.stream().filter(x -> !x.getIsPolled()
                        && !(x.getTeamId() == toAsk.getTeamId())
                        && x.getId() != last.getId()).collect(Collectors.toList());
            }
            return students.stream().filter(x -> toAsk != x && !x.getIsPolled() && x.getId() != last.getId()).collect(Collectors.toList());

        }
        return Collections.emptyList();
    }

    private Student getRandomMove(List<Student> students) {
        if (students.isEmpty()) {
            return last;
        }
        return students.get(random.nextInt(students.size()));
    }

    public void clearQueue() {
        prev = null;
        last = null;
    }
}
