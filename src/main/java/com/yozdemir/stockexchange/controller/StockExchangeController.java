package com.yozdemir.stockexchange.controller;

import com.yozdemir.stockexchange.models.StockExchange;
import com.yozdemir.stockexchange.service.StockExchangeService;
import com.yozdemir.stockexchange.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    @Autowired
    private StockExchangeService stockExchangeService;
    @GetMapping
    public List<StockExchange> getAllStockExchanges() {
        return stockExchangeService.getAllStockExchanges().get();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getStockExchangeByName(@PathVariable String name) {
        Optional<StockExchange> stockExchange = stockExchangeService.getStockExchangeByName(name);
        if (stockExchange.isPresent()) {
            return ResponseEntity.ok(stockExchange.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<StockExchange> createStockExchange(@RequestBody StockExchange stockExchange) {
        Optional<StockExchange> createdStockExchange = stockExchangeService.createStockExchange(stockExchange);
        //return ResponseEntity.ok(createdStockExchange.get());
        if (createdStockExchange.isPresent()) {
            return ResponseEntity.ok(createdStockExchange.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{name}/add-stock")
    public Optional<StockExchange> addStockToExchange(@PathVariable String name, @RequestBody Stock stock) {
        return stockExchangeService.addStockToExchange(name, stock);
    }

    @DeleteMapping("/{name}/remove-stock")
    public Optional<StockExchange> removeStockFromExchange(@PathVariable String name, @RequestBody Stock stock) {
        return stockExchangeService.removeStockFromExchange(name, stock);
    }

}