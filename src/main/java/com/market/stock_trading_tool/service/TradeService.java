package com.market.stock_trading_tool.service;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.exception.TradeException;

import java.util.List;

public interface TradeService {
    void tradeStock(String stockSymbol, TradeDTO trade) throws TradeException;
    List<TradeDTO> getTrades(StockDTO stock);
    List<TradeDTO> getLatestTrades(StockDTO stock);

}
