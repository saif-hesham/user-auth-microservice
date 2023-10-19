package com.example.security.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserError {
    private Integer status;
    private String message;
    private Long timeStamp;
}
