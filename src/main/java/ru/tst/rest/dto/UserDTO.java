package ru.tst.rest.dto;

import lombok.Builder;
import lombok.Data;
import ru.tst.rest.domain.User;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserDTO {

    private Long id;

    @NotEmpty
    private String login;

    @NotEmpty
    private String name;

    private String password;

    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .build();
    }
}
