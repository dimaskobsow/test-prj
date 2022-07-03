package ru.tst.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tst.rest.domain.Animal;
import ru.tst.rest.domain.AnimalKind;
import ru.tst.rest.dto.CreateAnimalDTO;
import ru.tst.rest.exception.EntityNotFoundException;
import ru.tst.rest.repository.AnimalKindRepository;
import ru.tst.rest.repository.AnimalRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalService {
    private final UserService userService;
    private final AnimalRepository animalRepository;
    private final AnimalKindRepository animalKindRepository;

    public List<Animal> getAll() {
        return animalRepository.findAll().stream().toList();
    }

    public List<Animal> getAllByOwner(String login) {
        var user = userService.findOne(login);
        return animalRepository.findByOwnerId(user.getId()).stream().toList();
    }

    public Animal getOne(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Animal.class, id));
    }

    public Animal save(CreateAnimalDTO animal) {
        var newAnimal = new Animal();
        if (animal.getOwnerLogin() != null) {
            newAnimal.setOwner(userService.findOne(animal.getOwnerLogin()));
        }
        var kind = animalKindRepository.findById(animal.getKind())
                .orElseThrow(() -> new EntityNotFoundException(AnimalKind.class, animal.getKind()));

        newAnimal.setKind(kind);
        newAnimal.setName(animal.getName());
        newAnimal.setSex(animal.getSex());
        newAnimal.setBirthdate(animal.getBirthdate());
        return animalRepository.save(newAnimal);
    }
}
