package ru.tst.rest.dto;

import lombok.Builder;
import lombok.Data;
import ru.tst.rest.domain.Animal;
import ru.tst.rest.domain.Sex;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
public class CreateAnimalDTO {

    @NotEmpty
    private String name;

    private LocalDate birthdate;

    private Sex sex;

    private Long kind;

    private String ownerLogin;

}
