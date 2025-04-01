package org.aryak.springdata.utils;

import org.aryak.springdata.domain.AuthorUUID;
import org.aryak.springdata.domain.BookUUID;
import org.aryak.springdata.domain.Student;
import org.aryak.springdata.repository.AuthorUUIDRepository;
import org.aryak.springdata.repository.BookUUIDRepository;
import org.aryak.springdata.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    private final AuthorUUIDRepository authorUUIDRepository;

    private final BookUUIDRepository bookUUIDRepository;

    public DataInitializer(StudentRepository studentRepository, AuthorUUIDRepository authorUUIDRepository, BookUUIDRepository bookUUIDRepository) {
        this.studentRepository = studentRepository;
        this.authorUUIDRepository = authorUUIDRepository;
        this.bookUUIDRepository = bookUUIDRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        studentRepository.deleteAll();
        System.out.println("DataInitializer was invoked.");

        // insert few students
        var s1 = Student.builder().address("Mumbai").name("Aryak").build();
        var s2 = Student.builder().address("Pune").name("Aditya").build();
        var s3 = Student.builder().address("Michigan").name("Satvik").build();

        var students = List.of(s1, s2, s3);

        studentRepository.saveAll(students);

        AuthorUUID a1 = new AuthorUUID();
        a1.setName("Aryak");
        a1.setAddress("Mumbai");

        var savedAuthor = authorUUIDRepository.save(a1);
        System.out.println(savedAuthor.getId());

        BookUUID b1 = new BookUUID();
        b1.setName("Domain Driven Design");

        var savedBook = bookUUIDRepository.save(b1);
        System.out.println(savedBook.getId());
    }
}
