package ru.teamandersen.component;
/* 
23.01.2022: Alexey created this file inside the package: ru.teamandersen.component 
*/

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.teamandersen.entity.Student;
import ru.teamandersen.repository.StudentRepository;

@Component
public class InitDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final StudentRepository studentRepository;

    public InitDataLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createStudentIfNotExists(1L, "Alexey", "test");
        createStudentIfNotExists(2L, "Ivan", "test");
        createStudentIfNotExists(3L, "Kirill", "test");
        createStudentIfNotExists(4L, "Anton", "test");
    }

    private void createStudentIfNotExists(Long teamId, String firstname, String secondname) {
        studentRepository.save(new Student.Builder(teamId, firstname, secondname).build());
    }
}
