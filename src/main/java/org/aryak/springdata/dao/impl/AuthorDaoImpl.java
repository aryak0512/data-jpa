package org.aryak.springdata.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.aryak.springdata.dao.AuthorDao;
import org.aryak.springdata.domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
        return getEntityManager().find(Author.class, authorId);
    }

    @Override
    public Author findByName(String firstName, String lastName) {

        String sql = "SELECT a from Author a where a.firstName = :first_name and a.lastName = :last_name";
        TypedQuery<Author> authorTypedQuery = getEntityManager().createQuery(sql ,Author.class);
        authorTypedQuery.setParameter("first_name", firstName);
        authorTypedQuery.setParameter("last_name", lastName);
        return authorTypedQuery.getSingleResult();
    }

    @Override
    public Author addNewAuthor(Author author) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin(); // ------- start txn

        em.persist(author);          // hibernate may not insert right away
        em.flush();                  // force hibernate to persist

        em.getTransaction().commit(); // ------- end txn

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
        return null;
    }
}
