package com.apartment.management.service.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * A User Entity.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private Long phoneNumber;

    private Boolean status;

    private ZonedDateTime createdDate;

    private GenderDTO gender;

    private List<AuthorityRoleDTO> roles;
}