package org.aryak.springdata.repository;

import org.aryak.springdata.domain.Student;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * by using @DataJpaTest, spring will load minimum beans required to execute
 * code of the repository later. Entire context will not be initialised.
 * Command line runner will not run when this annotation is used
 * <p>
 * <p>
 * by using @TestMethodOrder, we can specify the order of executing the test cases
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@DataJpaTest
//@ComponentScan(basePackages = {"org.aryak.springdata.utils"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // tell spring to not replace with H2
class MyFirstJpaTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DataSource dataSource;

    @Test
    @Order(0)
    void printDatabaseUrl() throws SQLException {
        System.out.println("Database URL: " + dataSource.getConnection().getMetaData().getURL());
    }

    @Test
    @Order(value = 1)
    void checkIfBeanLoadedCorrectly() {
        assertThat(studentRepository).isNotNull();
    }

    //@Test
    @Order(value = 2)
    void checkIfDatabaseIsEmpty() {
        assertThat(studentRepository.count()).isZero();
    }

    //@Test
    @Order(value = 3)
    void checkIfDataInsertedCorrectly() {
        Student s1 = new Student();
        s1.setAddress("Pune");
        s1.setName("Aryak");
        studentRepository.save(s1);


        Student s2 = new Student();
        s2.setAddress("Pune");
        s2.setName("Aryak");
        studentRepository.save(s2);
        var count = studentRepository.count();
        System.out.println(count);
        assertThat(count).isEqualTo(2);
    }

}
