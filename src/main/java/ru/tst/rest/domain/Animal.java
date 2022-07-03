package ru.tst.rest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * A user.
 */
@Entity
@Table(name = "animal")
@Getter
@Setter
@ToString
public class Animal {

    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column
    private LocalDate birthdate;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kind_id", referencedColumnName = "id")
    private AnimalKind kind;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Animal)) {
            return false;
        }
        return id != null && id.equals(((Animal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
