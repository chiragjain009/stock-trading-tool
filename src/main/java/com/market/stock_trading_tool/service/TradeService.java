package com.market.stock_trading_tool.service;

import com.market.stock_trading_tool.dto.Stock;
import com.market.stock_trading_tool.dto.Trade;
import com.market.stock_trading_tool.exception.TradeException;

import java.util.List;

public interface TradeService {
    void tradeStock(String stockSymbol, Trade trade) throws TradeException;
    List<Trade> getTrades(Stock stock);
    List<Trade> getLatestTrades(Stock stock);

}
