package org.aryak.springdata.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.aryak.springdata.dao.AuthorDao;
import org.aryak.springdata.domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;
    private static final Logger log = LoggerFactory.getLogger(AuthorDaoImpl.class);

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * factory to procure entity manager
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Author findById(Long authorId) {
        try ( var entityManager = getEntityManager() ) {
            return entityManager.find(Author.class, authorId);
        }
    }

    @Override
    public Author findByName(String firstName, String lastName) {

        String sql = "SELECT a from Author a where a.firstName = :first_name and a.lastName = :last_name";
        try ( var entityManager = getEntityManager() ) {
            TypedQuery<Author> authorTypedQuery = entityManager.createQuery(sql, Author.class);
            authorTypedQuery.setParameter("first_name", firstName);
            authorTypedQuery.setParameter("last_name", lastName);
            return authorTypedQuery.getSingleResult();
        }
    }

    @Override
    public Author addNewAuthor(Author author) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin(); // ------- start txn

        em.persist(author);          // hibernate may not insert right away
        em.flush();                  // force hibernate to persist

        em.getTransaction().commit(); // ------- end txn
        em.close();
        log.info("Saved object to DB : {}", author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        var em = getEntityManager();
        em.getTransaction().begin();    // ------- start txn

        em.merge(author);
        em.flush();

        em.getTransaction().commit();   // ------- end txn
        em.close();
        return author;
    }

    @Override
    public List<Author> getAuthorsByLastName(String lastName) {

        try ( EntityManager em = getEntityManager(); ) {
            Query query = em.createQuery("SELECT a from Author a where a.lastName = :last_name");
            query.setParameter("last_name", lastName);
            return query.getResultList();
        }
    }
}
