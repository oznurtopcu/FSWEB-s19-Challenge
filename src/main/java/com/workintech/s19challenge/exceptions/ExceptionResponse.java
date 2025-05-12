package com.workintech.s19challenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResponse {
    private String message;
    private int status;
    private LocalDateTime dateTime;
}
