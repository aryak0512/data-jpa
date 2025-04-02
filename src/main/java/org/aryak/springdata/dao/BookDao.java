package org.aryak.springdata.dao;

import org.aryak.springdata.domain.Book;

public interface BookDao {

    Book getBookByIsbn(String isbn);
}
