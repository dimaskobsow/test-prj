package ru.tst.rest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * A user.
 */
@Entity
@Table(name = "animal_kind")
@Getter
@Setter
@ToString
public class AnimalKind {

    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimalKind)) {
            return false;
        }
        return id != null && id.equals(((AnimalKind) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
