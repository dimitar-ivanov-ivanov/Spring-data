package app.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Month;
import java.util.List;
import java.time.LocalDate;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.FEBRUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner
            (StudentRepository repository) {
        return args -> {
            Student dimitar = new Student(
                    "Dimitar",
                    LocalDate.of(1999, FEBRUARY, 23),
                    "dimitar.ivanov@gmail.com"
            );

            Student vasko = new Student(
                    "Vasko",
                    LocalDate.of(2002, Month.MARCH,3),
                    "vasko@gmail.com"
            );

            repository.saveAll(
                    List.of(dimitar,vasko)
            );
        };
    }
}
