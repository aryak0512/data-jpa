package org.aryak.springdata.repository;

import org.aryak.springdata.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {

        studentRepository.deleteAll();
        // insert few students
        var s1 = Student.builder().address("Mumbai").name("Aryak").build();
        var s2 = Student.builder().address("Pune").name("Aditya").build();
        var s3 = Student.builder().address("Michigan").name("Satvik").build();

        var students = List.of(s1, s2, s3);
        studentRepository.saveAll(students);
    }

    @Test
    void findAllStudents() {

        var allStudents = studentRepository.findAllStudents();
        assertNotNull(allStudents);
        assertEquals(3, allStudents.size());
    }

    @Test
    void findStudentsWithNameLike() {

        var students1 = studentRepository.findStudentsWithNameLike("v");
        assertEquals(1, students1.size());
    }

    //@Test
    void findStudentsBetweenRange() {

        int max = 3, min = 2;
        var studentsBetweenRange = studentRepository.findStudentsBetweenRange(min, max);
        assertEquals(2, studentsBetweenRange.size());

    }
}