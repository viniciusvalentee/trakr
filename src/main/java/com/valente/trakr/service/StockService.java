package com.valente.trakr.service;

import com.valente.trakr.client.BrapiClient;
import com.valente.trakr.client.response.BrapiStockResponse;
import com.valente.trakr.entity.Stock;
import com.valente.trakr.entity.StockPurchase;
import com.valente.trakr.repository.StockPurchaseRepository;
import com.valente.trakr.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    @Value("${stock.client.brapi.token}")
    private String token;

    private final StockRepository stockRepository;
    private final StockPurchaseRepository stockPurchaseRepository;
    private final BrapiClient brapiClient;

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

    public List<Stock> findAll() {
        List<Stock> stocks = stockRepository.findAll();

        stocks.forEach(stock -> {
            BrapiStockResponse brapiStockResponse = brapiClient.getStock(stock.getStock(), token);
            stock.setPrice(BigDecimal.valueOf(brapiStockResponse.getResults().get(0).getRegularMarketPrice()));
        });

        return stocks;
    }
}
