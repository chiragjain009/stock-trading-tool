package com.market.stock_trading_tool.repository;

import com.market.stock_trading_tool.dto.Stock;
import com.market.stock_trading_tool.dto.Trade;
import com.market.stock_trading_tool.exception.TradeException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StockRepository {

    Stock addStock(Stock stock);

    Optional<Stock> findBySymbol(String symbol);

    Set<Stock> getAllStocks();

    boolean addTrade(String symbol, Trade trade) throws TradeException;

    List<Trade> getTrades(final Stock stock);
}
