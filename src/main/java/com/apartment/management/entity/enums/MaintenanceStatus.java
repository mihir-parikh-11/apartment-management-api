package com.apartment.management.entity.enums;

import com.apartment.management.service.dto.MaintenanceStatusDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * A MaintenanceStatus
 */
@Getter
public enum MaintenanceStatus {
    PENDING("Pending", false), PAID("Paid", true), NOT_REQUIRED("Not Required", true);

    final String name;

    final boolean isVisibleInList;

    MaintenanceStatus(String name, boolean isVisibleInList) {
        this.name = name;
        this.isVisibleInList = isVisibleInList;
    }

    public static List<MaintenanceStatusDTO> getVisible() {
        return Arrays.stream(values()).filter(MaintenanceStatus::isVisibleInList)
                .map(value -> MaintenanceStatusDTO.builder()
                        .name(value.name).status(value).build()).toList();
    }
}
