package org.aryak.springdata.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "BOOK_UUID")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookUUID {

    /**
     * Stores the UUID in binary format, more efficient
     * RFC 4122 complaint way
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(strategy = "uuid2", name = "uuid2")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;
}
