package com.market.stock_trading_tool.service.impl;

import com.market.stock_trading_tool.dto.Stock;
import com.market.stock_trading_tool.dto.Trade;
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
    public void tradeStock(String stockSymbol, Trade trade) throws TradeException {
        if(trade == null || trade.getQuantity() <= 0 || trade.getTradePrice() <= 0) {
            throw new TradeException("Request is not valid");
        }
        stockRepository.addTrade(stockSymbol,trade);

    }

    @Override
    public List<Trade> getTrades(Stock stock) {
        return stockRepository.getTrades(stock);
    }

    @Override
    public List<Trade> getLatestTrades(Stock stock) {
        final List<Trade> allTrades = this.getTrades(stock);
        final Instant fifteenMinutesBack = Instant.now().minus(15, ChronoUnit.MINUTES);

        if(allTrades!=null || allTrades.size()==0){
            return allTrades.stream()
                    .filter(trade -> trade.getTimeStamp().isAfter(fifteenMinutesBack))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();

    }
}
