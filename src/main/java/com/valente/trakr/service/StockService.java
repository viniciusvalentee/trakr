package com.valente.trakr.service;

import com.valente.trakr.entity.Stock;
import com.valente.trakr.entity.StockPurchase;
import com.valente.trakr.repository.StockPurchaseRepository;
import com.valente.trakr.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockPurchaseRepository stockPurchaseRepository;

    public Stock savePurchase(Stock stock, StockPurchase stockPurchase) {

        StockPurchase savedStockPurchased = stockPurchaseRepository.save(stockPurchase);
        stock.setPurchases(List.of(savedStockPurchased));
        return stockRepository.save(stock);
    }

    public Stock addPurchase(String stockId, StockPurchase stockPurchase) {
        Optional<Stock> optStock = stockRepository.findById(stockId);

        return optStock.map(stock -> {
            StockPurchase savedStockPurchased = stockPurchaseRepository.save(stockPurchase);
            stock.getPurchases().add(savedStockPurchased);
            return stockRepository.save(stock);
        }).orElseThrow(() -> new IllegalArgumentException("Cannot find a stock."));
    }
}
