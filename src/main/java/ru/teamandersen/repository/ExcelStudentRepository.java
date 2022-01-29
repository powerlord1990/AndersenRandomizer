package ru.teamandersen.repository;
/* 
27.01.2022: Alexey created this file inside the package: ru.teamandersen.repository 
*/

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.teamandersen.entity.Student;

//TODO Fix
@Repository
public interface ExcelStudentRepository extends CrudRepository<Student, Long> {
}
