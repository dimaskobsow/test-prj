package ru.tst.rest.dto.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private int code;
    private String message;
}
