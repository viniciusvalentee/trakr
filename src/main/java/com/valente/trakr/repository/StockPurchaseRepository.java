package com.valente.trakr.repository;

import com.valente.trakr.entity.Stock;
import com.valente.trakr.entity.StockPurchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPurchaseRepository extends MongoRepository<StockPurchase, String> {
}
