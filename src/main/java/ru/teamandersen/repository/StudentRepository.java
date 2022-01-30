package ru.teamandersen.repository;/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamandersen.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByIsAskedIsFalse();
    List<Student> findStudentsByIsPolledIsFalse();
}
