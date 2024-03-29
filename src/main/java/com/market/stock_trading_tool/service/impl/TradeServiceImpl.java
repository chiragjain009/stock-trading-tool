package com.market.stock_trading_tool.service.impl;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.exception.TradeException;
import com.market.stock_trading_tool.repository.StockRepository;
import com.market.stock_trading_tool.service.TradeService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl implements TradeService {

    private StockRepository stockRepository;

    public TradeServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void tradeStock(String stockSymbol, TradeDTO trade) throws TradeException {
        if(trade == null || trade.getQuantity() <= 0 || trade.getTradePrice() <= 0) {
            throw new TradeException("Request is not valid");
        }
        stockRepository.addTrade(stockSymbol,trade);

    }

    @Override
    public List<TradeDTO> getTrades(StockDTO stock) {
        return stockRepository.getTrades(stock);
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
