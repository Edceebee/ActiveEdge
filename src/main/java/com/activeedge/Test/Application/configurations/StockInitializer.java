package com.activeedge.Test.Application.configurations;

import com.activeedge.Test.Application.request.CreateStockDto;
import com.activeedge.Test.Application.response.ApiResponse;
import com.activeedge.Test.Application.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class StockInitializer {

  @Autowired
  private StockService stockService;

  @Bean
  public CommandLineRunner loadStocks() {
    return args -> {
      Timestamp now = new Timestamp(System.currentTimeMillis());
      List<CreateStockDto> initialStocks = Arrays.asList(
          new CreateStockDto("Dangote Group", 240.50, now, now),
          new CreateStockDto("Guinness Nigeria", 120.75, now, now),
          new CreateStockDto("MTN Nigeria", 320.25, now, now),
          new CreateStockDto("Zenith Bank", 55.80, now, now),
          new CreateStockDto("Shoprite Nigeria", 85.60, now, now),
          new CreateStockDto("Nestle Nigeria", 1450.00, now, now),
          new CreateStockDto("Nigeria Breweries", 42.70, now, now),
          new CreateStockDto("UBA", 7.25, now, now),
          new CreateStockDto("Access Bank", 8.10, now, now),
          new CreateStockDto("First Bank", 10.50, now, now),
          new CreateStockDto("Forte Oil", 20.35, now, now),
          new CreateStockDto("Total Nigeria", 114.80, now, now),
          new CreateStockDto("Oando", 5.45, now, now),
          new CreateStockDto("Seplat Petroleum", 650.00, now, now),
          new CreateStockDto("Lafarge Africa", 15.35, now, now),
          new CreateStockDto("Dangote Cement", 210.50, now, now),
          new CreateStockDto("Cadbury Nigeria", 9.60, now, now),
          new CreateStockDto("Honeywell Flour", 1.45, now, now),
          new CreateStockDto("Flour Mills Nigeria", 26.70, now, now),
          new CreateStockDto("PZ Cussons Nigeria", 5.80, now, now),
          new CreateStockDto("Transcorp", 0.99, now, now),
          new CreateStockDto("Julius Berger Nigeria", 21.50, now, now),
          new CreateStockDto("Union Bank", 5.60, now, now),
          new CreateStockDto("Stanbic IBTC", 45.70, now, now),
          new CreateStockDto("GT Bank", 30.25, now, now),
          new CreateStockDto("Wema Bank", 0.65, now, now),
          new CreateStockDto("Sterling Bank", 1.50, now, now),
          new CreateStockDto("Fidelity Bank", 2.15, now, now),
          new CreateStockDto("Ecobank", 7.70, now, now),
          new CreateStockDto("International Breweries", 5.90, now, now)
      );

      for (CreateStockDto stockDto : initialStocks) {
        ApiResponse<?> response = stockService.createStock(stockDto);
        if (!response.getStatus()) {
          log.error("Failed to create stock: " + stockDto.getName());
        }
      }
      log.info("Stocks loaded successfully!!!");
    };
  }
}
