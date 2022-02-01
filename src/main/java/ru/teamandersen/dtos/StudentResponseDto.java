package ru.teamandersen.dtos;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.dtos 
*/

import lombok.Data;
import ru.teamandersen.entity.Student;

@Data
public class StudentResponseDto {
    private Long id;
    private Long teamId;
    private String firstname;
    private String surname;
    private Integer score;
    private Boolean isAsked;
    private Boolean isPolled;

    public StudentResponseDto(Student student) {
        this.id = student.getId();
        this.teamId = student.getTeamId();
        this.firstname = student.getFirstname();
        this.surname = student.getSurname();
        this.score = student.getScore();
        this.isAsked = student.getIsAsked();
        this.isPolled = student.getIsPolled();
    }
}
