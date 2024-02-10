package com.hsb.rms.repository.impl;

import com.hsb.rms.domain.Item;
import com.hsb.rms.exception.ItemNotFoundException;
import com.hsb.rms.repository.ItemRepository;
import com.hsb.rms.service.ItemService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    public void initDb() {
        Random random = new Random();
        List<Item> items = IntStream.rangeClosed(1, 200).mapToObj(i -> {
            Item item = new Item();
            item.setName("Item # " + i);
            item.setUnit(random.nextInt(1000) + "gram");
            item.setPrice(random.nextLong(5000));
            item.setDetails("Auto generated");
            return item;
        }).collect(Collectors.toList());

        this.itemRepository.saveAll(items);
    }

    @Override
    public Item save(Item item) {
        item = itemRepository.save(item);
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
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item findOne(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            throw new ItemNotFoundException(id);
        }

        return item.get();
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
