package com.market.stock_trading_tool.repository;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.exception.TradeException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StockRepository {

    StockDTO addStock(StockDTO stock);

    Optional<StockDTO> findBySymbol(String symbol);

    Set<StockDTO> getAllStocks();

    boolean addTrade(String symbol, TradeDTO trade) throws TradeException;

    List<TradeDTO> getTrades(final StockDTO stock);
}
