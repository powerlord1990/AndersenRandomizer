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
        createStudentIfNotExists(1L, "Alexey");
        createStudentIfNotExists(2L, "Ivan");
        createStudentIfNotExists(2L, "Kirill");
        createStudentIfNotExists(2L, "Anton");
        createStudentIfNotExists(2L, "Ilnaz");
        createStudentIfNotExists(2L, "Satoshi");
        createStudentIfNotExists(2L, "2134");
        createStudentIfNotExists(2L, "345");
        createStudentIfNotExists(2L, "4554667");
        createStudentIfNotExists(2L, "6456");
        createStudentIfNotExists(2L, "234");
//        createStudentIfNotExists(7L, "4356");
//        createStudentIfNotExists(7L, "2134");
//        createStudentIfNotExists(8L, "6456");
//        createStudentIfNotExists(8L, "234");
//        createStudentIfNotExists(8L, "23");
//        createStudentIfNotExists(9L, "45743345");
//        createStudentIfNotExists(9L, "756345");
//        createStudentIfNotExists(10L, "7567345");
//        createStudentIfNotExists(10L, "76534");
//        createStudentIfNotExists(11L, "8567345");
    }

    private void createStudentIfNotExists(Long teamId, String firstname) {
        studentRepository.save(new Student.Builder(teamId, firstname, "test").build());
    }
}
