package com.campus.lostandfound.dto;

import com.campus.lostandfound.model.Item.Status;
import jakarta.validation.constraints.NotNull;

public record StatusUpdateRequest(
        @NotNull(message = "Status is required (OPEN or RECOVERED)")
        Status status
) {}