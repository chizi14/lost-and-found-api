package com.campus.lostandfound.repository;

import com.campus.lostandfound.model.Item;
import com.campus.lostandfound.model.Item.ItemType;
import com.campus.lostandfound.model.Item.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContainingIgnoreCase(String name);

    List<Item> findByCategoryIgnoreCase(String category);

    List<Item> findByLocationContainingIgnoreCase(String location);

    List<Item> findByTypeAndStatus(ItemType type, Status status);
}