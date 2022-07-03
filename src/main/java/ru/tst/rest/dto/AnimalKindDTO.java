package ru.tst.rest.dto;

import lombok.Builder;
import lombok.Data;
import ru.tst.rest.domain.AnimalKind;

@Data
@Builder
public class AnimalKindDTO {
    private Long id;
    private String name;

    public static AnimalKindDTO fromEntity(AnimalKind animalKind) {
        if (animalKind == null) {
            return null;
        }
        return AnimalKindDTO.builder()
                .id(animalKind.getId())
                .name(animalKind.getName())
                .build();
    }
}
