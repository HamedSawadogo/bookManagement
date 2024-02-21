package com.bankmanageemnt.banktp.dto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@Data
public class LoginRequest {
    @NotBlank
    @Column(unique = true)
    private String username;

    private String password;
}
