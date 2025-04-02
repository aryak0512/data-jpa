package org.aryak.springdata.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.aryak.springdata.dao.BookDao;
import org.aryak.springdata.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory emf;

    private static final Logger log = LoggerFactory.getLogger(BookDaoImpl.class);

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        String sql = "select b from Book b where b.isbn = :is_bn";
        try ( var entityManager = getEntityManager() ) {
            TypedQuery<Book> bookTypedQuery = entityManager.createQuery(sql, Book.class);
            bookTypedQuery.setParameter("is_bn", isbn);
            Book book = bookTypedQuery.getSingleResult();
            log.info("Book retrieved : {}", book);
            return book;
        }
    }
}
