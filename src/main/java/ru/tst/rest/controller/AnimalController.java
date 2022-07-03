package ru.tst.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tst.rest.dto.AnimalDTO;
import ru.tst.rest.dto.CreateAnimalDTO;
import ru.tst.rest.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @GetMapping("/all")
    public ResponseEntity<List<AnimalDTO>> getAll() {
        return ResponseEntity.ok(
                service.getAll().stream().map(AnimalDTO::fromEntity).toList()
        );
    }

    @GetMapping("/owner/{login}")
    public ResponseEntity<List<AnimalDTO>> getByOwner(@PathVariable("login") String login) {
        return ResponseEntity.ok(
                service.getAllByOwner(login).stream().map(AnimalDTO::fromEntity).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                AnimalDTO.fromEntity(service.getOne(id))
        );
    }

    @PostMapping("/")
    public ResponseEntity<AnimalDTO> create(@RequestBody CreateAnimalDTO animal) {
        return ResponseEntity.ok(
                AnimalDTO.fromEntity(service.save(animal))
        );
    }
}
