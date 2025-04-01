package org.aryak.springdata.repository;

import org.aryak.springdata.domain.AuthorComposite;
import org.aryak.springdata.domain.AuthorEmbedded;
import org.aryak.springdata.domain.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
