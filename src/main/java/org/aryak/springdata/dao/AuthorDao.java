package org.aryak.springdata.dao;

import org.aryak.springdata.domain.Author;

public interface AuthorDao {

    Author findById(Long authorId);

    Author findByName(String firstName, String lastName);

    Author addNewAuthor(Author author);

    Author updateAuthor(Author author);
}
