package com.hsb.rms.service.impl;

import com.hsb.rms.domain.Item;
import com.hsb.rms.exception.ItemNotFoundException;
import com.hsb.rms.repository.ItemRepository;
import com.hsb.rms.service.ItemService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        item = itemRepository.save(item);
        log.info("Item created:" + item.getName());
        return item;
    }

    @Override
    public Optional<Item> update(Item item) {
        return itemRepository
                .findById(item.getId())
                .map(itemRepository::save);
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        log.info("Fetching item list.");
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item findOne(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            throw new ItemNotFoundException(id);
        }

        log.info("Item found:" + item.get().getName());
        return item.get();
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
