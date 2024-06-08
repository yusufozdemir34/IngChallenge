package com.yozdemir.stockexchange.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class StockExchange {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    private boolean liveInMarket;
    
    @OneToMany(mappedBy = "stockExchange")
    private List<Stock> stocks = new ArrayList<>();

    public StockExchange(String name, String description, boolean liveInMarket) {
        this.name = name;
        this.description = description;
        this.liveInMarket = liveInMarket;
    }

    public StockExchange() {
    }

}
