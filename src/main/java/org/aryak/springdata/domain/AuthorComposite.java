package org.aryak.springdata.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHOR_COMPOSITE")
@IdClass(value = NameId.class)
public class AuthorComposite {

    @Id
    private String firstName;

    @Id
    private String lastName;

    private String country;
}
