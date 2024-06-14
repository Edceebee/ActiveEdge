package com.activeedge.Test.Application.service.impl;

import com.activeedge.Test.Application.models.Stocks;
import com.activeedge.Test.Application.repository.StocksRepo;
import com.activeedge.Test.Application.request.CreateStockDto;
import com.activeedge.Test.Application.request.UpdateStockPriceDto;
import com.activeedge.Test.Application.response.ApiResponse;
import com.activeedge.Test.Application.response.ApiResponse.Code;
import com.activeedge.Test.Application.service.StockService;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

  @Autowired
  private StocksRepo stocksRepo;

  @Override
  public ApiResponse<Stocks> createStock(CreateStockDto createStockDto) {
    try {
      Stocks newStock = new Stocks();
      BeanUtils.copyProperties(createStockDto, newStock);
      Stocks savedStocks = stocksRepo.save(newStock);
      return new ApiResponse<>(true, Code.SUCCESS, "Stocks saved successfully", savedStocks);
    }
    catch (Exception exception) {
      log.error("An exception occurred when creating stocks: {}", exception.getMessage(), exception);
      return new ApiResponse<>(false, Code.BAD_REQUEST, "AN ERROR OCCURRED WHEN CREATING STOCKS", null);
    }
  }

  @Override
  public ApiResponse<HashMap<String, Object>> getAllStocks(int page, int size) {
    try {
      HashMap<String, Object> response = new HashMap<>();

      Pageable pageable = PageRequest.of(page, size);

      Page<Stocks> stocksList = stocksRepo.findAll(pageable);

      response.put("stocks", stocksList);
      response.put("currentPage", stocksList.getNumber());
      response.put("totalItems", stocksList.getTotalElements());
      response.put("totalPages", stocksList.getTotalPages());

      return new ApiResponse<>(true, Code.SUCCESS, "Stocks retrieved successfully", response);
    }
    catch (Exception exception) {
      log.error("An exception occurred when retrieving stocks: {}", exception.getMessage(), exception);
      return new ApiResponse<>(false, Code.UNKNOWN_ERROR, "AN ERROR OCCURRED WHEN RETRIEVING STOCKS", null);
    }
  }

  @Override
  public ApiResponse<Stocks> getStockById(Long id) {
    try {
      Optional<Stocks> stock = stocksRepo.findById(id);
      if (stock.isPresent()) {
        return new ApiResponse<>(true, Code.SUCCESS, "Stock retrieved successfully", stock.get());
      } else {
        return new ApiResponse<>(false, Code.NOT_FOUND, "Stock with id "+id +" not found", null);
      }
    }
    catch (Exception exception) {
      log.error("An exception occurred when retrieving stock: {}", exception.getMessage(), exception);
      return new ApiResponse<>(false, Code.UNKNOWN_ERROR, "AN ERROR OCCURRED WHEN RETRIEVING STOCK", null);
    }
  }

  @Override
  public ApiResponse<Stocks> updateStockPrice(Long id, UpdateStockPriceDto updateStockPriceDto) {
    try {
      Optional<Stocks> stockOptional = stocksRepo.findById(id);
      if (stockOptional.isPresent()) {
        Stocks stock = stockOptional.get();
        stock.setCurrentPrice(updateStockPriceDto.getCurrentPrice());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        stock.setUpdateDate(now);

        Stocks updatedStock = stocksRepo.save(stock);
        return new ApiResponse<>(true, Code.SUCCESS, "Stock price updated successfully", updatedStock);
      } else {
        return new ApiResponse<>(false, Code.NOT_FOUND, "Stock with id "+id +" not found", null);
      }
    }
    catch (Exception exception) {
      log.error("An exception occurred when updating stock price: {}", exception.getMessage(), exception);
      return new ApiResponse<>(false, Code.BAD_REQUEST, "AN ERROR OCCURRED WHEN UPDATING STOCK PRICE", null);
    }
  }
}
