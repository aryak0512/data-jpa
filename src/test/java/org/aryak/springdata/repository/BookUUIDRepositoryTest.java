package org.aryak.springdata.repository;

import org.aryak.springdata.domain.BookUUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // tell spring to not replace with H2
@DataJpaTest
@ComponentScan(basePackages = {"org.aryak.springdata.utils"})
class BookUUIDRepositoryTest {

    @Autowired
    BookUUIDRepository bookUUIDRepository;

    @Test
    void testIfContextLoadedCorrectly(){
        assertThat(bookUUIDRepository).isNotNull();
    }

    @Test
    void testIfUuidGotGeneratedCorrectly(){

        BookUUID book = bookUUIDRepository.findFirstByName("Domain Driven Design");
        assertThat(book).isNotNull();
        assertThat(book.getId()).isNotNull();
    }
}