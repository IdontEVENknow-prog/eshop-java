package cz.elen.eshop_spring.repository;

import cz.elen.eshop_spring.model.OrderLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends MongoRepository<OrderLog, String> {
    // MongoRepository nám automaticky dodá NoSQL operace bez psaní kódu
}
