package com.hsb.rms.service.impl;

import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.ItemTestSamples;
import com.hsb.rms.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ItemServiceImplTest {

    @InjectMocks
    ItemServiceImpl itemService;
    @Mock
    ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        // Given
        Item item = ItemTestSamples.getItemSample1();
        // Mock the calls
        Mockito.when(itemRepository.save(item)).thenReturn(item);

        // When
        Item savedItem = itemRepository.save(item);

        // Then
        Assertions.assertEquals(item.getName(), savedItem.getName());
        Assertions.assertEquals(item.getPrice(), savedItem.getPrice());
        Assertions.assertEquals(item.getUnit(), savedItem.getUnit());
        Assertions.assertEquals(item.getDetails(), savedItem.getDetails());
    }
}