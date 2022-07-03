package ru.tst.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tst.rest.domain.User;
import ru.tst.rest.dto.UserDTO;
import ru.tst.rest.exception.LoginAlreadyUsedException;
import ru.tst.rest.exception.EntityNotFoundException;
import ru.tst.rest.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        userRepository
                .findOneByLogin(userDTO.getLogin().toLowerCase())
                .ifPresent(existingUser -> {
                    throw new LoginAlreadyUsedException();
                });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        newUser.setPassword(encryptedPassword);
        newUser.setName(userDTO.getName());
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User findOne(String login) {
        return userRepository.findOneByLogin(login.toLowerCase())
                .orElseThrow(() -> new EntityNotFoundException(User.class, login));
    }
}
