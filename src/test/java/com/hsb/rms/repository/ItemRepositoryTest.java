package com.hsb.rms.repository;

import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.ItemTestSamples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository repositoryUt; // Under test

    @Test
    void existItem() {
        // given
        Item item1 = ItemTestSamples.getItemSample1();
        ItemTestSamples.addAuditingEntity(item1);
        repositoryUt.save(item1);

        // When
        boolean itemExist = repositoryUt.existsById(item1.getId());

        // Then
        assertEquals(true, itemExist);
    }

    @Test
    void shouldBeSaved() {
        // given
        Item item1 = ItemTestSamples.getItemSample1();
        ItemTestSamples.addAuditingEntity(item1);
        repositoryUt.save(item1);

        // When
        Item retrievedItem = repositoryUt.getReferenceById(item1.getId());

        // Then
        assertEquals(item1.getId(), retrievedItem.getId());
        Assertions.assertEquals(item1.getName(), retrievedItem.getName());
        Assertions.assertEquals(item1.getPrice(), retrievedItem.getPrice());
        Assertions.assertEquals(item1.getUnit(), retrievedItem.getUnit());
        Assertions.assertEquals(item1.getDetails(), retrievedItem.getDetails());

    }
}