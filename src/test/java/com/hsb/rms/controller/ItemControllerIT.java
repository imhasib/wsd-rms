package com.hsb.rms.controller;

import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.ItemTestSamples;
import com.hsb.rms.repository.ItemRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerIT {

    private static RestTemplate restTemplate;
    @LocalServerPort
    private int PORT;
    private String BASE_URL = "http://localhost";
    private String endPoint;
    private String API_VERSION = "/api/v1";
    private Item item1;
    private Item item2;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void initTest() {
        endPoint = BASE_URL.concat(":").concat(PORT + "").concat(API_VERSION).concat("/items");

        item1 = ItemTestSamples.getItemSample1();
        ItemTestSamples.addAuditingEntity(item1);
        item2 = ItemTestSamples.getItemSample2();
        ItemTestSamples.addAuditingEntity(item2);
    }

    @Test
    void createItem() {
        Item response = restTemplate.postForObject(endPoint, item1, Item.class);
        assertEquals(item1.getName(), response.getName());
        assertEquals(item1.getUnit(), response.getUnit());
        assertEquals(item1.getPrice(), response.getPrice());
    }

    @Test
    @Sql(statements = "INSERT INTO t_item (id,name,price,unit, created_date, created_by) VALUES (1,'B', 10, 'gram','2024-02-15 05:05:36.613773+00','system')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getItem() {
        Item student = restTemplate.getForObject(endPoint + "/1", Item.class);
        assertEquals("B", student.getName());
    }

    @Test
    public void getItems() {
        HashMap<String, Object> itemsPage = restTemplate.getForObject(endPoint, HashMap.class);
        List<Item> items = (List<Item>) itemsPage.get("content");

        assertEquals(10, items.size());
    }

    @Test
    public void deleteItems() {
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(endPoint + "/1", HttpMethod.DELETE, new HttpEntity<>(null), Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }
}