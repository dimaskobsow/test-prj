package ru.tst.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tst.rest.domain.AnimalKind;


@Repository
public interface AnimalKindRepository extends JpaRepository<AnimalKind, Long> {
}
