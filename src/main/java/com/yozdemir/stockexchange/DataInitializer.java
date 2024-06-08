package com.yozdemir.stockexchange;

import com.yozdemir.stockexchange.models.Stock;
import com.yozdemir.stockexchange.models.StockExchange;
import com.yozdemir.stockexchange.repository.StockExchangeRepository;
import com.yozdemir.stockexchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StockExchangeRepository stockExchangeRepository;
    @Autowired
    private StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {
        java.util.List<StockExchange> stockExchanges = new ArrayList<>();

        stockExchanges.add(new StockExchange("NYSE", "New York Stock Exchange", false));
        stockExchanges.add(new StockExchange("NASDAQ", "NASDAQ Stock Market", false));
        stockExchanges.add(new StockExchange("LSE", "London Stock Exchange", false));
        stockExchanges.add(new StockExchange("JPX", "Japan Exchange Group", false));
        stockExchanges.add(new StockExchange("SSE", "Shanghai Stock Exchange", false));

        stockExchangeRepository.saveAll(stockExchanges);

        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            stocks.add(new Stock("Stock" + i, "Description for Stock " + i, BigDecimal.valueOf(100 + i), new Timestamp(System.currentTimeMillis())));
        }

        stockRepository.saveAll(stocks);

        // Assign stocks to each stock exchange
        for (StockExchange stockExchange : stockExchanges) {
            stockExchange.getStocks().addAll(stocks);
            stockExchange.setLiveInMarket(true); // Setting liveInMarket to true since each exchange has 5 stocks now
            stockExchangeRepository.save(stockExchange);
        }
    }
}


