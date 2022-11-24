package com.example.stock.repository;


import com.example.stock.model.StockData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StockRepository extends MongoRepository<StockData, String> {

    List<StockData> findStockDataByStock(String stock);

}
