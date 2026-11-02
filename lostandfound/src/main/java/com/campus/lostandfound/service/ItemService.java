package com.campus.lostandfound.service;

import com.campus.lostandfound.dto.ItemRequest;
import com.campus.lostandfound.dto.ItemResponse;
import com.campus.lostandfound.dto.StatusUpdateRequest;
import com.campus.lostandfound.exception.ItemNotFoundException;
import com.campus.lostandfound.model.Item;
import com.campus.lostandfound.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public ItemResponse create(ItemRequest request) {
        Item item = new Item();
        apply(item, request);
        return ItemResponse.from(repository.save(item));
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAll() {
        return repository.findAll().stream().map(ItemResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ItemResponse findById(Long id) {
        return ItemResponse.from(getOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> search(String name, String category, String location) {
        return repository.findAll().stream()
                .filter(i -> isBlank(name) || contains(i.getName(), name))
                .filter(i -> isBlank(category) || i.getCategory().equalsIgnoreCase(category.trim()))
                .filter(i -> isBlank(location) || contains(i.getLocation(), location))
                .map(ItemResponse::from)
                .toList();
    }

    public ItemResponse update(Long id, ItemRequest request) {
        Item item = getOrThrow(id);
        apply(item, request);
        return ItemResponse.from(item);
    }

    public ItemResponse updateStatus(Long id, StatusUpdateRequest request) {
        Item item = getOrThrow(id);
        item.setStatus(request.status());
        return ItemResponse.from(item);
    }

    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    private Item getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    private void apply(Item item, ItemRequest request) {
        item.setName(request.name());
        item.setDescription(request.description());
        item.setCategory(request.category());
        item.setLocation(request.location());
        item.setType(request.type());
        if (request.status() != null) {
            item.setStatus(request.status());
        } else if (item.getStatus() == null) {
            item.setStatus(Item.Status.OPEN);
        }
        if (request.dateReported() != null) {
            item.setDateReported(request.dateReported());
        } else if (item.getDateReported() == null) {
            item.setDateReported(LocalDate.now());
        }
        item.setContact(request.contact());
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private boolean contains(String field, String search) {
        return field != null && field.toLowerCase().contains(search.trim().toLowerCase());
    }
}
