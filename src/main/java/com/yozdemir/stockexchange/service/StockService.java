package com.yozdemir.stockexchange.service;

import com.yozdemir.stockexchange.exception.ResourceNotFoundException;
import com.yozdemir.stockexchange.models.Stock;
import com.yozdemir.stockexchange.models.StockExchange;
import com.yozdemir.stockexchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findByStockExchange(StockExchange stockExchange) {
        return stockRepository.findByStockExchange(stockExchange);
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStockPrice(Long id, BigDecimal newPrice) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found with id " + id));
        stock.setCurrentPrice(newPrice);
        stock.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found with id " + id));
        stockRepository.delete(stock);
    }
}
