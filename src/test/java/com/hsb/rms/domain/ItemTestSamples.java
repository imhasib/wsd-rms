package com.hsb.rms.domain;

import java.time.Instant;

public class ItemTestSamples {

    public static Item getItemSample1() {
        return new Item().id(1L).name("name1").price(1D).unit("unit1").details("details1");
    }

    public static Item getItemSample2() {
        return new Item().id(2L).name("name2").price(2D).unit("unit2").details("details2");
    }

    public static void addAuditingEntity(Item item) {
        item.setCreatedBy("testUser@example.com");
        item.setCreatedDate(Instant.now());
    }
}
