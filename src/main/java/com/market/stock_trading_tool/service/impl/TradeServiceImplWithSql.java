package com.market.stock_trading_tool.service.impl;


import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.entity.StockEntity;
import com.market.stock_trading_tool.entity.TradeEntity;
import com.market.stock_trading_tool.exception.TradeException;
import com.market.stock_trading_tool.repository.StockRepositoryWithSql;
import com.market.stock_trading_tool.repository.TradeRepositoryWithSql;
import com.market.stock_trading_tool.service.CoustomMapper;
import com.market.stock_trading_tool.service.TradeService;
import com.market.stock_trading_tool.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeServiceImplWithSql implements TradeService {

    private StockRepositoryWithSql stockRepository;
    private TradeRepositoryWithSql tradeRepository;
    @Autowired
    private CoustomMapper userMapper;


    public TradeServiceImplWithSql(StockRepositoryWithSql stockRepository, TradeRepositoryWithSql tradeRepository) {
        this.stockRepository = stockRepository;
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void tradeStock(String stockSymbol, TradeDTO trade) throws TradeException {
        if(trade == null || trade.getQuantity() <= 0 || trade.getTradePrice() <= 0) {
            throw new TradeException("Request is not valid");
        }
        TradeEntity tradeEntity=userMapper.tradeDTOToTradeEntity(trade);
        tradeEntity.setStock(stockRepository.getById(stockSymbol));
        tradeRepository.save(tradeEntity);

    }

    @Override
    public List<TradeDTO> getTrades(StockDTO stock) {
       StockEntity stockEntity=stockRepository.getById(stock.getSymbol());
       return userMapper.tradeEntityListToTradeDTOList(stockEntity.getTrades());
    }

    @Override
    public List<TradeDTO> getLatestTrades(StockDTO stock) {
        final List<TradeDTO> allTrades = this.getTrades(stock);
        final Instant fifteenMinutesBack = Instant.now().minus(15, ChronoUnit.MINUTES);

        if(allTrades!=null || allTrades.size()==0){
            return allTrades.stream()
                    .filter(trade -> trade.getTimeStamp().isAfter(fifteenMinutesBack))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();

    }
}
