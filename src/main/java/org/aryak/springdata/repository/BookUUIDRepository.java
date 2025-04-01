package org.aryak.springdata.repository;

import org.aryak.springdata.domain.BookUUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookUUIDRepository extends JpaRepository<BookUUID, UUID> {
    BookUUID findFirstByName(String aryak);
}
