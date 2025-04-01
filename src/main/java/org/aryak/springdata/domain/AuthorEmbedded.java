package org.aryak.springdata.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTHOR_COMPOSITE")
public class AuthorEmbedded {

    @EmbeddedId
    private NameId nameId;
    private String country;

}
