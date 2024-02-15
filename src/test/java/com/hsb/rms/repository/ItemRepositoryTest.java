package com.hsb.rms.repository;

import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.ItemTestSamples;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository repositoryUt; // Under test

    @Autowired
    EntityManager entityManager;
    private Item item1;
    private Item item2;

    @Test
    void existItem() {
        // given
        repositoryUt.save(item1);

        // When
        boolean itemExist = repositoryUt.existsById(item1.getId());

        // Then
        assertEquals(true, itemExist);
    }

    @BeforeEach
    public void initTest() {
        item1 = ItemTestSamples.getItemSample1();
        ItemTestSamples.addAuditingEntity(item1);
        item2 = ItemTestSamples.getItemSample2();
        ItemTestSamples.addAuditingEntity(item2);
    }

    @Test
    void shouldBeSaved() {
        repositoryUt.save(item1);

        // When
        Item retrievedItem = repositoryUt.getReferenceById(item1.getId());

        // Then
        assertEquals(item1.getId(), retrievedItem.getId());
        assertEquals(item1.getName(), retrievedItem.getName());
        assertEquals(item1.getPrice(), retrievedItem.getPrice());
        assertEquals(item1.getUnit(), retrievedItem.getUnit());
        assertEquals(item1.getDetails(), retrievedItem.getDetails());
    }

    @Test
    @Transactional
    void createItemWithExistingId() throws Exception {
        repositoryUt.save(item1);

        item2.setId(item1.getId());
        repositoryUt.save(item2);

        long count = repositoryUt.count();

        assertEquals(1, count);

    }

    @Test
    void createItemWithExistingName() {
        repositoryUt.save(item1);

        item2.setName(item1.getName());
        assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
            repositoryUt.save(item2);
            entityManager.flush();
        });
    }
}