package org.aryak.springdata.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // tell spring to not replace with H2
@DataJpaTest
@ComponentScan(basePackages = {"org.aryak.springdata.utils"})
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

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