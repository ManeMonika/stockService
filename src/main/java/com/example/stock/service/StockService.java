package com.example.stock.service;


import com.example.stock.dto.StockDataDto;
import com.example.stock.dto.StockDataWrapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Rest Api to update/read stock data
 */

@Service
public interface StockService {


    public StockDataWrapper getStock(String stockName) throws Exception;

    public StockDataDto addStockData(StockDataDto dataDto) throws Exception;

    public StockDataWrapper bulkUpdateStockData(List<StockDataDto> stockDTO) throws Exception;
}
