package com.example.stock.dto;

import java.util.List;

public class StockDataWrapper {

    private Integer totalNumberOfRecords;
    private List<StockDataDto> stockData;

    public Integer getTotalNumberOfRecords() {
        return totalNumberOfRecords;
    }

    public void setTotalNumberOfRecords(Integer totalNumberOfRecords) {
        this.totalNumberOfRecords = totalNumberOfRecords;
    }

    public List<StockDataDto> getStockData() {
        return stockData;
    }

    public void setStockData(List<StockDataDto> stockData) {
        this.stockData = stockData;
    }
}
