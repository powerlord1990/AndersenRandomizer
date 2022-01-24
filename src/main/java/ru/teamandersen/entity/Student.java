package ru.teamandersen.entity;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.entity 
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "SECONDNAME")
    private String secondname;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "IS_ASKED")
    private Boolean isAsked;

    @Column(name = "IS_POLLED")
    private Boolean isPolled;


    public static class Builder {
        private final Long teamId;
        private final String firstname;
        private final String secondname;
        private Integer score = 0;
        private Boolean isAsked = false;
        private Boolean isPolled = false;

        public Builder(Long teamId, String firstname, String secondname) {
            this.teamId = teamId;
            this.firstname = firstname;
            this.secondname = secondname;
        }

        public Builder score(int score) {
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
        secondname = builder.secondname;
        score = builder.score;
        isAsked = builder.isAsked;
        isPolled = builder.isPolled;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", score=" + score +
                ", isAsked=" + isAsked +
                ", isPolled=" + isPolled +
                '}';
    }
}
