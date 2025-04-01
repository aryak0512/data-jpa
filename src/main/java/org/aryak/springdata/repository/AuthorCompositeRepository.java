package org.aryak.springdata.repository;

import org.aryak.springdata.domain.AuthorComposite;
import org.aryak.springdata.domain.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
