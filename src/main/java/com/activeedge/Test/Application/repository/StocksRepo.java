package com.activeedge.Test.Application.repository;

import com.activeedge.Test.Application.models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepo extends JpaRepository<Stocks, Long> {

}
