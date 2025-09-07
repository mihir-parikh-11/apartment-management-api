package com.apartment.management.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A FlatOwnerDTO
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FlatOwnerDTO implements Serializable {

    private Long id;

    @Email(message = "Invalid email format")
    private String email;

    private Long phoneNumber;

    private String firstName;

    private String lastName;

    private GenderDTO gender;
}
