package com.hsb.rms.controller;

import com.hsb.rms.domain.Item;
import com.hsb.rms.exception.InvalidInputException;
import com.hsb.rms.exception.ItemNotFoundException;
import com.hsb.rms.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        item = itemService.save(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<Item> updateItem(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody Item item
    ) {
        if (item.getId() == null) {
            throw new ItemNotFoundException();
        }

        if (!Objects.equals(id, item.getId())) {
            new InvalidInputException("id");
        }
        itemService.findOne(id);
        Optional<Item> result = itemService.update(item);

        return ResponseEntity.ok(result.get());
    }

    @GetMapping("")
    public ResponseEntity<Page<Item>> getItems(Pageable pageable) {
        Page itemPage = itemService.findAll(pageable);
        return new ResponseEntity<>(itemPage, HttpStatus.OK);
    }

    /**
     * {@code GET  /items/:id} : get the "id" item.
     *
     * @param id the id of the itemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@NotNull @PathVariable("id") Long id) {
        Item item = itemService.findOne(id);
        return ResponseEntity.ok(item);
    }

    /**
     * {@code DELETE  /items/:id} : delete the "id" item.
     *
     * @param id the id of the itemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
