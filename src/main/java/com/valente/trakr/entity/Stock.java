package com.valente.trakr.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stock")
public class Stock {

    @Id
    private String id;

    private String stock;

    @Transient
    private BigDecimal price; // Não salva no banco, vem da controller

    @DBRef
    private List<StockPurchase> purchases;

    //private User user;
}
