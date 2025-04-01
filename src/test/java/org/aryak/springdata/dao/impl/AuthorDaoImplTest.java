package org.aryak.springdata.dao.impl;

import org.aryak.springdata.dao.AuthorDao;
import org.aryak.springdata.domain.Author;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = {"org.aryak.springdata.dao"})
class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    DataSource dataSource;

    @Test
    @Order(0)
    void testWhichDatabaseGettingConnected() throws SQLException {
        var url = dataSource.getConnection().getMetaData().getURL();
        System.out.println("The url is : " + url);
        assertThat(url).isNotNull();
        assertThat(url).contains("h2");
    }

    @Test
    void testIfContextLoadedCorrectly() {
        assertThat(authorDao).isNotNull();
    }

    @Test
    void testFindById() {

        Author a = new Author();
        a.setFirstName("Aryak");
        a.setLastName("Deshpande");
        var author = authorDao.addNewAuthor(a);
        var author1 = authorDao.findById(author.getId());
        assertThat(author1).isNotNull();

    }

    @Test
    void testFindByName() {

        // given
        String firstName = "Aryak";
        String lastName = "Deshpande";
        Author a = new Author();
        a.setFirstName(firstName);
        a.setLastName(lastName);

        // when
        authorDao.addNewAuthor(a);
        var author1 = authorDao.findByName(firstName, lastName);

        // then
        assertThat(author1).isNotNull();
    }

    @Test
    void testAddNewAuthor() {

        // given
        Author a = new Author();
        a.setFirstName("Aryak");
        a.setLastName("Deshpande");

        // when
        var author = authorDao.addNewAuthor(a);

        // then
        assertThat(author).isNotNull();
        assertThat(author.getId()).isNotNull();
    }

    @Test
    void testUpdateAuthor() {

        // given
        Author a = new Author();
        a.setFirstName("Aryak");
        a.setLastName("Deshpande");
        var author = authorDao.addNewAuthor(a);

        // when
        author.setLastName("Desh");

        // then
        assertThat(author.getLastName()).isEqualTo("Desh");

    }
}