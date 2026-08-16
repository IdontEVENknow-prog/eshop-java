package cz.elen.eshop_spring.repository;

import cz.elen.eshop_spring.model.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private Long currentId = 1L;

    public Order save(Order order) {
        order.setId(currentId++);
        orders.add(order);
        System.out.println("Analytický log: Objednávka ID " + order.getId() + " byla úspěšně uložena do paměti.");
        return order;
    }

    public List<Order> findAll() {
        return orders;
    }
}
