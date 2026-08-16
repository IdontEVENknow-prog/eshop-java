package cz.elen.eshop_spring.service;

import cz.elen.eshop_spring.model.Order;
import cz.elen.eshop_spring.model.OrderLog;
import cz.elen.eshop_spring.repository.OrderLogRepository;
import cz.elen.eshop_spring.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLogRepository orderLogRepository; // Nově injektujeme MongoDB repozitář

    public OrderService(OrderRepository orderRepository, OrderLogRepository orderLogRepository) {
        this.orderRepository = orderRepository;
        this.orderLogRepository = orderLogRepository;
    }

    public Order createOrder(Order order) {
        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }

        double calculatedTotal = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotalPrice(calculatedTotal);

        // 1. ZÁPIS DO RELAČNÍ DATABÁZE (MySQL)
        Order savedOrder = orderRepository.save(order);
        System.out.println("Analytický log [MySQL]: Objednávka uložena pod ID: " + savedOrder.getId());

        // 2. ZÁPIS DO NOSQL DATABÁZE (MongoDB)
        try {
            OrderLog log = new OrderLog(
                    savedOrder.getId(),
                    calculatedTotal,
                    "Nová objednávka byla úspěšně zpracována a uložena do hlavního systému."
            );
            orderLogRepository.save(log);
            System.out.println("Analytický log [MongoDB]: Auditní záznam pro objednávku " + savedOrder.getId() + " byl úspěšně zapsán do NoSQL.");
        } catch (Exception e) {
            System.err.println("Chyba při zápisu do MongoDB logu: " + e.getMessage());
        }

        return savedOrder;
    }
}
