package com.example.stock.controller;


import com.example.stock.dto.StockDataDto;
import com.example.stock.dto.StockDataWrapper;
import com.example.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Rest Controller to fetch or modify the stock data
 *
 */

@RestController
@RequestMapping(value = {"stock"})
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = {"/data/get/{stockName}"} , name = "Get The Stock Data for stock Name")
    public StockDataWrapper getStock(@PathVariable String stockName) throws Exception {

        return stockService.getStock(stockName);
    }

    @PostMapping(value = {"/data/add"})
    public StockDataDto addStockData(@RequestBody StockDataDto stockDTO) throws Exception {
        return stockService.addStockData(stockDTO);
    }


    @PostMapping(value = {"/data/add/bulk"})
    public StockDataWrapper bulkUpdateStockData(@RequestBody List<StockDataDto> stockDTO) throws Exception {
        return stockService.bulkUpdateStockData(stockDTO);
    }


}
