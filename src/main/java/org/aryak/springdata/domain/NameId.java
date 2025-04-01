package org.aryak.springdata.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class NameId implements Serializable {

    private String firstName;
    private String lastName;

    public NameId() {
    }

    public NameId(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == null || getClass() != o.getClass() ) return false;

        NameId nameId = (NameId) o;
        return Objects.equals(firstName, nameId.firstName) && Objects.equals(lastName, nameId.lastName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        return result;
    }
}
