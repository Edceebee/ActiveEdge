package com.activeedge.Test.Application;

import com.activeedge.Test.Application.models.Stocks;
import com.activeedge.Test.Application.repository.StocksRepo;
import com.activeedge.Test.Application.request.CreateStockDto;
import com.activeedge.Test.Application.request.UpdateStockPriceDto;
import com.activeedge.Test.Application.response.ApiResponse;
import com.activeedge.Test.Application.response.ApiResponse.Code;
import com.activeedge.Test.Application.service.impl.StockServiceImpl;
import java.util.Collections;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestApplicationTests {

	@Mock
	private StocksRepo stocksRepo;

	@InjectMocks
	private StockServiceImpl stockService;

	private Stocks stock;
	private CreateStockDto createStockDto;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setup() {
		stock = new Stocks();
		stock.setId(1L);
		stock.setName("Dangote Group");
		stock.setCurrentPrice(240.50);
		stock.setCreateDate(LocalDateTime.now());
		stock.setUpdateDate(LocalDateTime.now());

		createStockDto = new CreateStockDto();
		createStockDto.setName("Dangote Group");
		createStockDto.setCurrentPrice(240.50);
		createStockDto.setCreateDate(LocalDateTime.now());
		createStockDto.setUpdateDate(LocalDateTime.now());
	}

	@Test
	public void testCreateStockSuccess() {
		when(stocksRepo.save(any(Stocks.class))).thenReturn(stock);

		ApiResponse<?> response = stockService.createStock(createStockDto);

		assertTrue(response.getStatus());
		assertEquals(Code.SUCCESS, response.getCode());
		assertEquals("Stocks saved successfully", response.getMessage());
		assertNotNull(response.getData());
	}

	@Test
	public void testCreateStockFailure() {
		when(stocksRepo.save(any(Stocks.class))).thenThrow(new RuntimeException("Save failed"));

		ApiResponse<?> response = stockService.createStock(createStockDto);

		assertFalse(response.getStatus());
		assertEquals(Code.BAD_REQUEST, response.getCode());
		assertEquals("AN ERROR OCCURRED WHEN CREATING STOCKS", response.getMessage());
		assertNull(response.getData());
	}

	@Test
	public void testGetAllStocks() {
		List<Stocks> stocksList = Collections.singletonList(stock);
		Page<Stocks> stockPage = new PageImpl<>(stocksList);
		Pageable pageable = PageRequest.of(0, 5);

		when(stocksRepo.findAll(pageable)).thenReturn(stockPage);

		ApiResponse<HashMap<String, Object>> response = stockService.getAllStocks(0, 5);
		HashMap<String, Object> data = response.getData();

		assertTrue(response.getStatus());
		assertEquals(Code.SUCCESS, response.getCode());
		assertEquals("Stocks retrieved successfully", response.getMessage());
		assertNotNull(data);
	}

	@Test
	public void testGetStockByIdSuccess() {
		when(stocksRepo.findById(1L)).thenReturn(Optional.of(stock));

		ApiResponse<Stocks> response = stockService.getStockById(1L);

		assertTrue(response.getStatus());
		assertEquals(Code.SUCCESS, response.getCode());
		assertEquals("Stock retrieved successfully", response.getMessage());
		assertNotNull(response.getData());
		assertEquals(stock, response.getData());
	}

	@Test
	public void testGetStockByIdFailure() {
		when(stocksRepo.findById(1L)).thenReturn(Optional.empty());

		ApiResponse<Stocks> response = stockService.getStockById(1L);

		assertFalse(response.getStatus());
		assertEquals(Code.NOT_FOUND, response.getCode());
		assertEquals("Stock not found", response.getMessage());
		assertNull(response.getData());
	}

	@Test
	public void testUpdateStockSuccess() {
		when(stocksRepo.findById(1L)).thenReturn(Optional.of(stock));
		when(stocksRepo.save(any(Stocks.class))).thenReturn(stock);
		UpdateStockPriceDto updateStockPriceDto = new UpdateStockPriceDto(250.00);

		ApiResponse<Stocks> response = stockService.updateStockPrice(1L, updateStockPriceDto);

		assertTrue(response.getStatus());
		assertEquals(Code.SUCCESS, response.getCode());
		assertEquals("Stock price updated successfully", response.getMessage());
		assertNotNull(response.getData());
		assertEquals(250.00, response.getData().getCurrentPrice());
	}

	@Test
	public void testUpdateStockFailure() {
		when(stocksRepo.findById(1L)).thenReturn(Optional.empty());

		UpdateStockPriceDto updateStockPriceDto = new UpdateStockPriceDto(250.00);

		ApiResponse<Stocks> response = stockService.updateStockPrice(1L, updateStockPriceDto);

		assertFalse(response.getStatus());
		assertEquals(Code.NOT_FOUND, response.getCode());
		assertEquals("Stock not found", response.getMessage());
		assertNull(response.getData());
	}

}
