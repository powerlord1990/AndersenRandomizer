package ru.teamandersen.repository;/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.repository 
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamandersen.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByFirstname(String firstname);
    Optional<Student> findStudentByFirstnameAndSurname(String firstname, String surname);
    List<Student> findStudentByIsAskedIsFalse();
    List<Student> findStudentByIsPolledIsFalse();
}
