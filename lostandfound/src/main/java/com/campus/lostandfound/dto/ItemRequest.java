package com.campus.lostandfound.dto;

import com.campus.lostandfound.model.Item.ItemType;
import com.campus.lostandfound.model.Item.Status;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ItemRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100)
        String name,

        @Size(max = 1000)
        String description,

        @NotBlank(message = "Category is required")
        String category,

        @NotBlank(message = "Location is required")
        String location,

        @NotNull(message = "Type is required (LOST or FOUND)")
        ItemType type,

        Status status,

        @PastOrPresent(message = "Date cannot be in the future")
        LocalDate dateReported,

        @NotBlank(message = "Contact is required")
        String contact
) {}