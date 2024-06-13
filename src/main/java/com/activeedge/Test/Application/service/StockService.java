package com.activeedge.Test.Application.service;

import com.activeedge.Test.Application.models.Stocks;
import com.activeedge.Test.Application.request.CreateStockDto;
import com.activeedge.Test.Application.request.UpdateStockPriceDto;
import com.activeedge.Test.Application.response.ApiResponse;
import java.util.HashMap;

public interface StockService {


  ApiResponse<Stocks> createStock(CreateStockDto createStockDto);

  ApiResponse<HashMap<String, Object>> getAllStocks(int page, int size);

  ApiResponse<Stocks> getStockById(Long id);

  ApiResponse<?> updateStockPrice(Long id, UpdateStockPriceDto updateStockPriceDto);

}
