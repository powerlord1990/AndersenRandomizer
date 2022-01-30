package ru.teamandersen.entity;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.entity 
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "STUDENTS")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEAM_ID")
    private Long teamId;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SCORE")
    private BigDecimal score;

    @Column(name = "IS_ASKED")
    private Boolean isAsked;

    @Column(name = "IS_POLLED")
    private Boolean isPolled;

    public static class Builder {
        private final Long teamId;
        private final String firstname;
        private final String surname;
        private BigDecimal score = BigDecimal.valueOf(0);
        private Boolean isAsked = false;
        private Boolean isPolled = false;

        public Builder(Long teamId, String firstname, String surname) {
            this.teamId = teamId;
            this.firstname = firstname;
            this.surname = surname;
        }

        public Builder score(BigDecimal score) {
            this.score = score;
            return this;
        }

        public Builder isAsked(boolean isAsked) {
            this.isAsked = isAsked;
            return this;
        }

        public Builder isPolled(boolean isPolled) {
            this.isPolled = isPolled;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    private Student(Builder builder) {
        teamId = builder.teamId;
        firstname = builder.firstname;
        surname = builder.surname;
        score = builder.score;
        isAsked = builder.isAsked;
        isPolled = builder.isPolled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id)
                && Objects.equals(teamId, student.teamId)
                && Objects.equals(firstname, student.firstname)
                && Objects.equals(surname, student.surname)
                && Objects.equals(score, student.score)
                && Objects.equals(isAsked, student.isAsked)
                && Objects.equals(isPolled, student.isPolled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId, firstname, surname, score, isAsked, isPolled);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", score=" + score +
                ", isAsked=" + isAsked +
                ", isPolled=" + isPolled +
                '}';
    }
}
