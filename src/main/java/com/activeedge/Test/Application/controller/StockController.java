package com.activeedge.Test.Application.controller;

import com.activeedge.Test.Application.models.Stocks;
import com.activeedge.Test.Application.request.CreateStockDto;
import com.activeedge.Test.Application.request.UpdateStockPriceDto;
import com.activeedge.Test.Application.response.ApiResponse;
import com.activeedge.Test.Application.service.StockService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stocks")
public class StockController {

  @Autowired
  private StockService stockService;


  @PostMapping("")
  public ApiResponse<Stocks> createStock(@RequestBody CreateStockDto createStockDto) {
    return stockService.createStock(createStockDto);
  }

  @GetMapping("")
  public ApiResponse<HashMap<String, Object>> getAllStocks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return stockService.getAllStocks(page, size);
  }

  @GetMapping("/{id}")
  public ApiResponse<Stocks> getStockById(@PathVariable Long id) {
    return stockService.getStockById(id);
  }

  @PutMapping("/{id}")
  public ApiResponse<?> updateStockPrice(@PathVariable Long id, @RequestBody UpdateStockPriceDto updateStockPriceDto) {
    return stockService.updateStockPrice(id, updateStockPriceDto);
  }

}
