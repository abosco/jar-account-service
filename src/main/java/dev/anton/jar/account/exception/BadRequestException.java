package dev.anton.jar.account.exception;

import dev.anton.model.Error;

public class BadRequestException extends RuntimeException {

    private final Error error;

    public BadRequestException(String code, String message) {
        this.error = new Error().code(code).message(message);
    }

    public Error getError() {
        return error;
    }
}
