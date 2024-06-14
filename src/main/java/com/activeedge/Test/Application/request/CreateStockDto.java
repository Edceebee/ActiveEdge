package com.activeedge.Test.Application.request;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStockDto {
  private String name;
  private double currentPrice;
  private Timestamp createDate;
  private Timestamp updateDate;

}
