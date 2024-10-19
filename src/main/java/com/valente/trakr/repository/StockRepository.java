package com.valente.trakr.repository;

import com.valente.trakr.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, String> {
}
