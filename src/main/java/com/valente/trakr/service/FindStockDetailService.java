package com.valente.trakr.service;


import com.valente.trakr.client.BrapiClient;
import com.valente.trakr.client.response.BrapiStockDataResponse;
import com.valente.trakr.client.response.BrapiStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindStockDetailService {

    @Value("${stock.client.brapi.token}")
    private String token;

    private final BrapiClient brapiClient;

    @Cacheable(value = "stock", key = "#stock")
    public Optional<BrapiStockDataResponse> getBrapiStockDetail(String stock) {
        log.info("Checking stock infos: {} in Brapi", stock);
        BrapiStockResponse brapiStockResponse = brapiClient.getStock(stock, token);

        log.info("Brapi stock return {}: {}", stock, brapiStockResponse.getResults().get(0));
        return Optional.of(brapiStockResponse.getResults().get(0));
    }

}
