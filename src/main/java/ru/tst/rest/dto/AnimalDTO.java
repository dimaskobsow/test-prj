package ru.tst.rest.dto;

import lombok.Builder;
import lombok.Data;
import ru.tst.rest.domain.Animal;
import ru.tst.rest.domain.Sex;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
public class AnimalDTO {

    private Long id;

    @NotEmpty
    private String name;

    private LocalDate birthdate;

    private Sex sex;

    private AnimalKindDTO kind;

    private UserDTO owner;

    public static AnimalDTO fromEntity(Animal animal) {
        if (animal == null) {
            return null;
        }
        return AnimalDTO.builder()
                .id(animal.getId())
                .name(animal.getName())
                .birthdate(animal.getBirthdate())
                .sex(animal.getSex())
                .kind(AnimalKindDTO.fromEntity(animal.getKind()))
                .owner(UserDTO.fromEntity(animal.getOwner()))
                .build();
    }
}
