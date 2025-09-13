package com.apartment.management.service.dto;

import com.apartment.management.entity.enums.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A MaintenanceStatusDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceStatusDTO {

    private String name;

    private MaintenanceStatus status;
}
