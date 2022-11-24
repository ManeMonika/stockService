package com.example.stock.service;

import com.example.stock.dto.StockDataDto;
import com.example.stock.dto.StockDataWrapper;
import com.example.stock.mapper.StockMapper;
import com.example.stock.model.StockData;
import com.example.stock.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @InjectMocks
    StockServiceImpl stockServiceImpl;

    private StockMapper mapper = Mappers.getMapper(StockMapper.class);
    StockRepository stockRepository = mock(StockRepository.class);

    @Test
    public void shouldReturnStockDataIfAvailable() throws Exception {

        //Given
        String stockName = "AA";

        //When
        List<StockData> stockDataList = new ArrayList<>();
        stockDataList.add(new StockData());
        when(stockRepository.findStockDataByStock(stockName)).thenReturn(stockDataList);
        StockDataWrapper stockData = stockServiceImpl.getStock(stockName);

        //Then
        Assertions.assertNotNull(stockData);
        verify(stockRepository, times(1)).findStockDataByStock(any());
        verifyNoMoreInteractions(stockRepository);
    }


    @Test
    public void shouldReturnThrowExceptionIfStockNameIsEmpty() throws Exception {

        Assertions.assertThrows(IllegalArgumentException.class, () -> stockServiceImpl.getStock(null));

        verifyNoInteractions(stockRepository);
    }


    @Test
    public void addStockData() throws Exception {

        //Given
        StockDataDto dataDto = new StockDataDto();
        dataDto.setQuarter("1");
        dataDto.setStock("ABC");

        //When
        StockData stockData = mapper.toStockData(dataDto);
        when(stockRepository.insert(any(StockData.class))).thenReturn(stockData);
        StockDataDto result = stockServiceImpl.addStockData(dataDto);

        //Then
        Assertions.assertNotNull(result);
        verify(stockRepository, times(1)).insert(any(StockData.class));
        verifyNoMoreInteractions(stockRepository);
    }

    @Test
    public void addBulkStockData() throws Exception {

        //Given
        List<StockDataDto> stockDTOList = new ArrayList<>();
        StockDataDto dataDto = new StockDataDto();
        dataDto.setQuarter("1");
        dataDto.setStock("ABC");
        stockDTOList.add(dataDto);

        //When
        List<StockData> stockDataList = mapper.toStockData(stockDTOList);
        when(stockRepository.insert(anyList())).thenReturn(stockDataList);
        StockDataWrapper result = stockServiceImpl.bulkUpdateStockData(stockDTOList);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalNumberOfRecords());
        verify(stockRepository, times(1)).insert(anyList());
        verifyNoMoreInteractions(stockRepository);
    }

    @Test
    public void shouldUpdateStockDataInBulk() throws Exception {

        //Given
        List<StockDataDto> stockDTOList = new ArrayList<>();
        StockDataDto dataDto = new StockDataDto();
        dataDto.setQuarter("1");
        dataDto.setStock("ABC");
        stockDTOList.add(dataDto);

        //When
        List<StockData> stockDataList = mapper.toStockData(stockDTOList);
        when(stockRepository.insert(anyList())).thenReturn(stockDataList);
        stockServiceImpl.bulkUpdate();

        //Then
        verify(stockRepository, times(1)).insert(anyList());
        verifyNoMoreInteractions(stockRepository);
    }

}
