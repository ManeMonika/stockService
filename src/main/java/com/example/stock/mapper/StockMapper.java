package com.example.stock.mapper;


import com.example.stock.dto.StockDataDto;
import com.example.stock.model.StockData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {

    StockDataDto toStockDataDto(StockData stockData);
    List<StockDataDto> toStockDataDto(List<StockData> stockData);

    StockData toStockData(StockDataDto stockData);
    List<StockData> toStockData(List<StockDataDto> stockData);

}
