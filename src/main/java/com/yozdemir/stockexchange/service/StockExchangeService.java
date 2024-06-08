package com.yozdemir.stockexchange.service;

import com.yozdemir.stockexchange.models.Stock;
import com.yozdemir.stockexchange.models.StockExchange;
import com.yozdemir.stockexchange.repository.StockExchangeRepository;
import com.yozdemir.stockexchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class StockExchangeService {

    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;

    public Optional<List<StockExchange>> getAllStockExchanges() {
        return Optional.ofNullable(stockExchangeRepository.findAll());
    }


    public Optional<StockExchange> getStockExchangeByName(String name) {
        return Optional.ofNullable(stockExchangeRepository.findByName(name).orElse(null));
    }

    public Optional<StockExchange> createStockExchange(StockExchange stockExchange) {
        return Optional.of(stockExchangeRepository.save(stockExchange));
    }

    public Optional<StockExchange> addStockToExchange(String exchangeName, Stock stock) {
        StockExchange exchange = stockExchangeRepository.findByName(exchangeName).orElseThrow();
        exchange.getStocks().add(stock);
        if (exchange.getStocks().size() >= 5) {
            exchange.setLiveInMarket(true);
        }
        return Optional.of(stockExchangeRepository.save(exchange));
    }

    public Optional<StockExchange> removeStockFromExchange(String exchangeName, Stock stock) {
        StockExchange exchange = stockExchangeRepository.findByName(exchangeName).orElseThrow();
        exchange.getStocks().remove(stock);
        if (exchange.getStocks().size() < 5) {
            exchange.setLiveInMarket(false);
        }
        return Optional.of(stockExchangeRepository.save(exchange));
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void updateStockPrice(Long stockId, BigDecimal newPrice) {
        Stock stock = stockRepository.findById(stockId).orElseThrow();
        stock.setCurrentPrice(newPrice);
        stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        stockRepository.save(stock);
    }

    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    public void deleteStockFromSystem(Stock stock) {
        stockRepository.delete(stock);
    }
}
