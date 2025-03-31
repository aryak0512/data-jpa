package org.aryak.springdata.utils;

import org.aryak.springdata.domain.Student;
import org.aryak.springdata.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DataInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataInitializer was invoked.");

        // insert few students
        var s1 = Student.builder().address("Mumbai").name("Aryak").build();
        var s2 = Student.builder().address("Pune").name("Aditya").build();
        var s3 = Student.builder().address("Michigan").name("Satvik").build();

        var students = List.of(s1, s2, s3);



    }
}
