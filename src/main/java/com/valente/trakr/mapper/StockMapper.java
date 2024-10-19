package com.valente.trakr.mapper;

import com.valente.trakr.controller.request.StockAddPurchaseRequest;
import com.valente.trakr.controller.request.StockRequest;
import com.valente.trakr.entity.Stock;
import com.valente.trakr.entity.StockPurchase;
import lombok.experimental.UtilityClass;
import org.springframework.data.util.Pair;

@UtilityClass
public class StockMapper {

    public static Pair<Stock, StockPurchase> toStock(StockRequest request) {

        final Stock stock = Stock.builder()
                .stock(request.getStock())
                .build();

        final StockPurchase stockPurchase = StockPurchase
                .builder()
                .date(request.getDate())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();

        return Pair.of(stock, stockPurchase);
    }

    public static StockPurchase toStockPurchase(StockAddPurchaseRequest request) {
        return StockPurchase.builder()
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .date(request.getDate())
                .build();
    }
}
