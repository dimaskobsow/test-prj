package ru.tst.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tst.rest.dto.UserDTO;
import ru.tst.rest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<UserDTO> userService(@RequestBody UserDTO user, HttpServletRequest request) throws ServletException {
        var registeredUser = userService.registerUser(user);
        request.login(user.getLogin(), user.getPassword());
        return ResponseEntity.ok(UserDTO.fromEntity(registeredUser));
    }

    @GetMapping("/user/check/{login}")
    public ResponseEntity<UserDTO> check(@PathVariable("login") String login) {
        return ResponseEntity.ok(UserDTO.fromEntity(userService.findOne(login)));
    }
}
