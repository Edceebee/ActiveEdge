package com.activeedge.Test.Application.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStockDto {
  private String name;
  private double currentPrice;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;

}
