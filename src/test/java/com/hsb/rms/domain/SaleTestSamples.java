package com.hsb.rms.domain;

public class SaleTestSamples {

    public static Sale getSaleSample1() {
        return new Sale().id(1L).quantity(1).unitPrice(1D);
    }

    public static Sale getSaleSample2() {
        return new Sale().id(2L).quantity(2).unitPrice(2D);
    }
}
