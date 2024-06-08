package com.yozdemir.stockexchange.repository;

import com.yozdemir.stockexchange.models.Stock;
import com.yozdemir.stockexchange.models.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByStockExchange(StockExchange stockExchange);
}