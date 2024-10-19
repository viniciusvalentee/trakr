package com.valente.trakr.controller;

import com.valente.trakr.controller.request.StockAddPurchaseRequest;
import com.valente.trakr.controller.request.StockRequest;
import com.valente.trakr.entity.Stock;
import com.valente.trakr.entity.StockPurchase;
import com.valente.trakr.mapper.StockMapper;
import com.valente.trakr.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> savePurchase(@RequestBody StockRequest request) {

        Pair<Stock, StockPurchase> stock = StockMapper.toStock(request);
        Stock savedStock = stockService.savePurchase(stock.getFirst(), stock.getSecond());
        return ResponseEntity.ok(savedStock);
    }

    @PostMapping("/add")
    public ResponseEntity<Stock> addPurchase(@RequestBody StockAddPurchaseRequest request) {
        try {
            Stock stock = stockService.addPurchase(request.getStockId(), StockMapper.toStockPurchase(request));
            return ResponseEntity.ok(stock);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }
}
