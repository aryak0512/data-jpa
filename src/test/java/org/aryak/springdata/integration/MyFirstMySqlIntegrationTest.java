package org.aryak.springdata.integration;

import org.aryak.springdata.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = {"local"}) // refer to local props file
@DataJpaTest
@ComponentScan(basePackages = {"org.aryak.springdata.utils"}) // execute CLR
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // tell spring to not replace with H2
class MyFirstMySqlIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testContext() {
        assertThat(studentRepository).isNotNull();
    }

    @Test
    @DisplayName(value = "Data initializer triggered")
    void checkIfTestDataWasLoadedCorrectlyFromCommandLineRunnerBean() {
        assertThat(studentRepository.count()).isEqualTo(3);
    }

}
