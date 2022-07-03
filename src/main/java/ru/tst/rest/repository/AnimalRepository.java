package ru.tst.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tst.rest.domain.Animal;

import java.util.List;


@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByOwnerId(Long id);
}
