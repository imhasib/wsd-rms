package com.hsb.rms.service;

import com.hsb.rms.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ItemService {
    /**
     * Save the item.
     *
     * @param item the item to save.
     * @return the persisted item.
     */
    Item save(Item item);

    /**
     * Updates the item.
     *
     * @param item the item to update.
     * @return the persisted item.
     */
    Optional<Item> update(Item item);

    /**
     * Get all the items.
     *
     * @param pageable the pagination information.
     * @return the list of items.
     */
    Page<Item> findAll(Pageable pageable);

    /**
     * Get the item by Id.
     *
     * @param id the id of the item.
     * @return the item.
     */
    Item findOne(Long id);

    /**
     * Delete the item by Id.
     *
     * @param id the id of the item.
     */
    void delete(Long id);
}
