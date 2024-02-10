package com.hsb.rms.service;

import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.User;
import com.hsb.rms.repository.ItemRepository;
import com.hsb.rms.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public DataInitializer(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateRandomUsers(10);
        generateItems(200);
    }

    private void generateRandomUsers(int count) {
        List<User> users = IntStream.rangeClosed(1, count).mapToObj(i -> {
            User user = new User();
            user.setName("User-" + i);
            user.setEmail("user" + i + "@random.com");
            user.setLogin(user.getEmail());
            user.setPassword("Test@123");
            user.setActivated(true);
            return user;
        }).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    public void generateItems(int count) {
        Random random = new Random();
        List<Item> items = IntStream.rangeClosed(1, count).mapToObj(i -> {
            Item item = new Item();
            item.setName("Item # " + i);
            item.setUnit(random.nextInt(1000) + " gram");
            item.setPrice(random.nextLong(5000));
            item.setDetails("Auto generated");
            return item;
        }).collect(Collectors.toList());

        this.itemRepository.saveAll(items);
    }
}
