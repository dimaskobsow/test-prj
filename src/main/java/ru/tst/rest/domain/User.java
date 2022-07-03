package ru.tst.rest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * A user.
 */
@Entity
@Table(name = "s_user")
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "last_fail")
    private Instant lastFail = null;

    @Column(name = "login_attempt")
    private Integer loginAttempt = 0;

    @Column(name = "blocked")
    private Boolean blocked = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
