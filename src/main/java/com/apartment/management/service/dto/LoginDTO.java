package com.apartment.management.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * A LoginDTO.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginDTO {

    @Email(message = "Invalid email format")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
