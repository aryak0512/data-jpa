package org.aryak.springdata;

import org.aryak.springdata.domain.Student;
import org.aryak.springdata.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    StudentRepository studentRepository;

    public SpringDataApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // insert few students
        var s1 = Student.builder().address("Mumbai").name("Aryak").build();
        var s2 = Student.builder().address("Pune").name("Aditya").build();
        var s3 = Student.builder().address("Michigan").name("Satvik").build();

        var students = List.of(s1, s2, s3);

        studentRepository.saveAll(students);
    }
}
