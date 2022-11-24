package com.example.stock.service;

import com.example.stock.dto.StockDataDto;
import com.example.stock.dto.StockDataWrapper;
import com.example.stock.mapper.StockMapper;
import com.example.stock.model.StockData;
import com.example.stock.repository.StockRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StockServiceImpl implements StockService{


    final StockRepository stockRepository;
    private StockMapper mapper = Mappers.getMapper(StockMapper.class);
    Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);


    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDataWrapper getStock(String stockName) throws IllegalArgumentException{

        if(! StringUtils.hasText(stockName) ) {
            throw new IllegalArgumentException("stock Name Should not be Empty");
        }
        logger.info("fetching stock data");
        List<StockData> stockList = stockRepository.findStockDataByStock(stockName);
        StockDataWrapper stockDataWrapper = new StockDataWrapper();
        stockDataWrapper.setTotalNumberOfRecords(stockList.size());
        stockDataWrapper.setStockData(mapper.toStockDataDto(stockList));
        return stockDataWrapper;
    }

    @Override
    public StockDataDto addStockData(StockDataDto dataDto) throws Exception {
        StockData stockData = mapper.toStockData(dataDto);
        stockData = stockRepository.insert(stockData);
        return mapper.toStockDataDto(stockData);
    }

    @Override
    public StockDataWrapper bulkUpdateStockData(List<StockDataDto> stockDTO) throws Exception {

        List<StockData> stockData = mapper.toStockData(stockDTO);
        stockData = stockRepository.insert(stockData);
        StockDataWrapper stockDataWrapper = new StockDataWrapper();
        stockDataWrapper.setTotalNumberOfRecords(stockData.size());
        stockDataWrapper.setStockData(mapper.toStockDataDto(stockData));
        return stockDataWrapper;
    }

    //Upload CSV File Directly which has bulk stock data
    @PostConstruct
    public void bulkUpdate() {

        try {

            Reader in = new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("testData.csv")).getFile());
            List<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in).getRecords();

            List<StockData> stockList = new ArrayList<>();
            for (CSVRecord record : records) {
                StockData stockData = new StockData();
                stockData.setQuarter(record.get("quarter"));
                stockData.setStock(record.get("stock"));
                stockData.setClose("close");
                stockList.add(stockData);
            }

            List<StockData> insertedStockList = stockRepository.insert(stockList);
            logger.info("Number of record inserted : ", insertedStockList.size());

        }catch (Exception e){
            e.printStackTrace();
            logger.error("Unable to upload the initial stock data");
        }
    }
}
