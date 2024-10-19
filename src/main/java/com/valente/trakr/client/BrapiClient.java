package com.valente.trakr.client;

import com.valente.trakr.client.response.BrapiStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BrapiClient", url = "${stock.client.brapi.url}")
public interface BrapiClient {

    @GetMapping("/{stock}?token={token}")
    BrapiStockResponse getStock(@PathVariable("stock") String stock, @PathVariable("token") String token);
}
