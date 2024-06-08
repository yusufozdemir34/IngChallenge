package com.yozdemir.stockexchange.controller;


import com.yozdemir.stockexchange.models.Stock;
import com.yozdemir.stockexchange.models.StockExchange;
import com.yozdemir.stockexchange.service.StockExchangeService;
import com.yozdemir.stockexchange.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
    private StockExchangeService stockExchangeService;

    @GetMapping("/exchange/{exchangeName}")
    public ResponseEntity<?> getStocksByExchange(@PathVariable String exchangeName) {
        Optional<StockExchange> stockExchange = stockExchangeService.getStockExchangeByName(exchangeName);
        List<Stock> stocks = stockService.findByStockExchange(stockExchange.get());
        return ResponseEntity.ok(stocks);
    }


    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.ok(createdStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStockPrice(@PathVariable Long id, @RequestBody Stock stockDetails) {
        Stock updatedStock = stockService.updateStockPrice(id, stockDetails.getCurrentPrice());
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
