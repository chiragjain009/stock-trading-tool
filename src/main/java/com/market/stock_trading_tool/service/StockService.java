package com.market.stock_trading_tool.service;

import com.market.stock_trading_tool.exception.StockMarketException;

public interface StockService {
    Double calculateDividendYield(Double price, String symbol) throws StockMarketException;
    Double getPERatio(Double price, String symbol) throws StockMarketException;
    Double getVolumeWeightedStock(String symbol) throws StockMarketException;
    Double calculateGeometricMean();

}
