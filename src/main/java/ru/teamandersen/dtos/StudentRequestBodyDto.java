package ru.teamandersen.dtos;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.dtos 
*/

import lombok.Data;
import ru.teamandersen.entity.Student;

@Data
public class StudentRequestBodyDto {
    private Long teamId;
    private String firstname;
    private String secondname;
    private Integer score;

    public StudentRequestBodyDto(Student student) {
        this.teamId = student.getTeamId();
        this.firstname = student.getFirstname();
        this.secondname = student.getSecondname();
        this.score = student.getScore();
    }
}
