package com.yozdemir.stockexchange.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal currentPrice;

    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "stock_exchange_id")
    private StockExchange stockExchange;

    public Stock(String name, String description, BigDecimal currentPrice, Timestamp lastUpdate) {
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

    public Stock() {
    }
}
