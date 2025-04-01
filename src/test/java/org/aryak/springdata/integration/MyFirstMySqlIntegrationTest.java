package org.aryak.springdata.integration;

import org.aryak.springdata.domain.AuthorComposite;
import org.aryak.springdata.domain.NameId;
import org.aryak.springdata.repository.AuthorCompositeRepository;
import org.aryak.springdata.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles(value = {"local"}) // refer to local props file
@DataJpaTest
@ComponentScan(basePackages = {"org.aryak.springdata.utils"}) // execute CLR
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // tell spring to not replace with H2
class MyFirstMySqlIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AuthorCompositeRepository authorCompositeRepository;

    @Test
    void testContext() {
        assertThat(studentRepository).isNotNull();
    }

    @Test
    @DisplayName(value = "Data initializer triggered")
    void checkIfTestDataWasLoadedCorrectlyFromCommandLineRunnerBean() {
        assertThat(studentRepository.count()).isEqualTo(3);
    }

    @Test
    void testCompositeKey(){
        AuthorComposite ac = new AuthorComposite();
        ac.setCountry("India");
        ac.setFirstName("Aryak");
        ac.setLastName("Deshpande");
        authorCompositeRepository.save(ac);

        NameId nameId = new NameId();
        nameId.setFirstName("Aryak");
        nameId.setLastName("Deshpande");
        var fetched = authorCompositeRepository.findById(nameId);

        assertThat(fetched).isNotNull();
        assertThat(fetched.isPresent()).isTrue();
    }

    @Test
    void testCompositeKeyNegativeScenario(){
        AuthorComposite ac = new AuthorComposite();
        ac.setCountry("India");
        ac.setFirstName("Aryak");
        ac.setLastName("Deshpande");
        authorCompositeRepository.save(ac);

        NameId nameId = new NameId();
        nameId.setFirstName("Aryak");
        nameId.setLastName("Deshpand");
        var fetched = authorCompositeRepository.findById(nameId);

        assertThat(fetched).isNotNull();
        assertTrue(fetched.isEmpty());
    }


}
