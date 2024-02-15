package com.hsb.rms.domain;

public class OrderTestSamples {

    public static Order getOrderSample1() {
        return new Order().id(1L).counter("counter1").instruction("instruction1");
    }

    public static Order getOrderSample2() {
        return new Order().id(2L).counter("counter2").instruction("instruction2");
    }
}
