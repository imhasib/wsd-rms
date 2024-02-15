package com.hsb.rms.domain;

public class UserTestSamples {

    public static User getUserTestSample1() {
        return new User().id(1L).login("user1").email("user1@example.com").password("Test@123").name("User1").activated(true);
    }

    public static User getUserTestSample2() {
        return new User().id(2L).login("user2").email("user2@example.com").password("Test@123").name("User2").activated(true);
    }
}
