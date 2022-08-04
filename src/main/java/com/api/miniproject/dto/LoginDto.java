package com.api.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginDto {

    @NotBlank
    String id;

    @NotBlank
    String password;
}
