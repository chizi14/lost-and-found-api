package com.campus.lostandfound.dto;

import com.campus.lostandfound.model.Item;
import java.time.LocalDate;

public record ItemResponse(
        Long id,
        String name,
        String description,
        String category,
        String location,
        Item.ItemType type,
        Item.Status status,
        LocalDate dateReported,
        String contact
) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getLocation(),
                item.getType(),
                item.getStatus(),
                item.getDateReported(),
                item.getContact()
        );
    }
}