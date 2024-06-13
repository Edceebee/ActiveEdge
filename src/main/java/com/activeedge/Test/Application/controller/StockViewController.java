package com.activeedge.Test.Application.controller;

import com.activeedge.Test.Application.response.ApiResponse;
import com.activeedge.Test.Application.service.StockService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockViewController {

  @Autowired
  private StockService stockService;

  @GetMapping("/stocks-view")
  public String showStocksView(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
    ApiResponse<HashMap<String, Object>> response = stockService.getAllStocks(page, size);
    HashMap<String, Object> data = response.getData();

    model.addAttribute("stocks", data.get("stocks"));
    model.addAttribute("currentPage", data.get("currentPage"));
    model.addAttribute("totalItems", data.get("totalItems"));
    model.addAttribute("totalPages", data.get("totalPages"));
    model.addAttribute("size", size);

    return "stocks";
  }


}
