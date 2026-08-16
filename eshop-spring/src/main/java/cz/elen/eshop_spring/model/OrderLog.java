package cz.elen.eshop_spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "order_logs") // Definuje název kolekce v MongoDB
public class OrderLog {

    @Id
    private String id; // MongoDB standardně používá jako ID textový řetězec (String/ObjectId)

    private Long orderId;
    private double totalPrice;
    private LocalDateTime timestamp;
    private String message;

    public OrderLog() {}

    public OrderLog(Long orderId, double totalPrice, String message) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.timestamp = LocalDateTime.now(); // Automaticky uloží aktuální čas logu
        this.message = message;
    }

    // Gettery a Settery
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
