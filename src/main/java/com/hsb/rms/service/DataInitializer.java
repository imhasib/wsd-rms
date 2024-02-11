package com.hsb.rms.service;

import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.Order;
import com.hsb.rms.domain.Sale;
import com.hsb.rms.domain.User;
import com.hsb.rms.domain.enumeration.PayType;
import com.hsb.rms.repository.ItemRepository;
import com.hsb.rms.repository.OrderRepository;
import com.hsb.rms.repository.SaleRepository;
import com.hsb.rms.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
@Slf4j
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final SaleRepository saleRepository;

    public DataInitializer(UserRepository userRepository, ItemRepository itemRepository, OrderRepository orderRepository, SaleRepository saleRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.saleRepository = saleRepository;
    }

    private final int NUMBER_OF_USERS = 10;
    private final int NUMBER_OF_ITEMS = 10;
    private final int NUMBER_OF_ORDERS = 100;
    private final int MAX_NUMBER_OF_SALES = 5;
    private final int MAX_QUANTITY_OF_ITEM = 3;
    private final int MAX_PRICE_OF_ITEM = 400;

    private List<User> users;
    private List<Item> items;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Generating dummy data...");
        users = generateRandomUsers(NUMBER_OF_USERS);
        items = generateItems(NUMBER_OF_ITEMS);
        finalizeOrders(generateOrder(NUMBER_OF_ORDERS));
        log.info("Data generation successful.");
    }

    private List<User> generateRandomUsers(int count) {
        List<User> users = IntStream.rangeClosed(1, count).mapToObj(i -> {
            User user = new User();
            user.setName("User-" + i);
            user.setEmail("user" + i + "@random.com");
            user.setLogin(user.getEmail());
            user.setPassword("Test@123");
            user.setActivated(true);
            return user;
        }).collect(Collectors.toList());

        return userRepository.saveAll(users);
    }

    public List<Item> generateItems(int count) {
        Random random = new Random();
        List<Item> items = IntStream.rangeClosed(1, count).mapToObj(i -> {
            Item item = new Item();
            item.setName("Item # " + i);
            item.setUnit(random.nextInt(150) + " gram");
            item.setPrice(Double.valueOf(random.nextInt(MAX_PRICE_OF_ITEM)));
            item.setDetails("Auto generated");
            return item;
        }).collect(Collectors.toList());

        return itemRepository.saveAll(items);
    }

    public List<Order> generateOrder(int count) {
        Random random = new Random();
        List<Order> orders = IntStream.rangeClosed(1, count).mapToObj(i -> {
            Order order = new Order();

            User servedBy = users.get(random.nextInt(users.size()));
            User customer = users.get(random.nextInt(users.size()));

            order.setCounter("Counter #" + (random.nextInt(4)+1));
            order.setServedBy(servedBy);
            order.setCustomer(customer);
            order.setPaidBy(PayType.CASH);
            order.setInstruction("Thanks!");
            return order;
        }).collect(Collectors.toList());

        return orderRepository.saveAll(orders);
    }

    public List<Order> finalizeOrders(List<Order> orders) {
        orders = orders.stream().map(order -> {
            List<Sale> sales = generateSales(order, MAX_NUMBER_OF_SALES);
            Double bill = sales.stream().mapToDouble(sale -> sale.getQuantity() * sale.getUnitPrice()).sum();
            order.setBill(bill);
            order.setSales(sales);
            return order;
        }).collect(Collectors.toList());

        return orderRepository.saveAll(orders);
    }

    public List<Sale> generateSales(Order order, int maxSales) {
        Random random = new Random();
        List<Sale> sales = IntStream.rangeClosed(1, random.nextInt(maxSales)).mapToObj(i -> {
            Sale sale = new Sale();

            Item item = items.get(random.nextInt(items.size()));;
            int qty = random.nextInt(MAX_QUANTITY_OF_ITEM) + 1;
            double total = qty * item.getPrice();

            sale.setItem(item);
            sale.setQuantity(qty);
            sale.setUnitPrice(item.getPrice());
            sale.setOrder(order);

            return sale;
        }).collect(Collectors.toList());

        return saleRepository.saveAll(sales);
    }
}
