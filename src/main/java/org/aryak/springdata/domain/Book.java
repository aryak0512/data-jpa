package org.aryak.springdata.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String author;
    private Long authorId;
}
