package ru.tst.rest.exception;

public class LoginAlreadyUsedException extends RuntimeException {
    public LoginAlreadyUsedException() {
        super("Login name already used!");
    }
}
