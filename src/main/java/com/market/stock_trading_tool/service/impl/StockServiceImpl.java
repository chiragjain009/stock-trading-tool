package com.market.stock_trading_tool.service.impl;

import com.market.stock_trading_tool.dto.Stock;
import com.market.stock_trading_tool.dto.StockType;
import com.market.stock_trading_tool.dto.Trade;
import com.market.stock_trading_tool.exception.StockMarketException;
import com.market.stock_trading_tool.repository.StockRepository;
import com.market.stock_trading_tool.service.StockService;
import com.market.stock_trading_tool.service.TradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    private StockRepository stockRepository;
    private TradeService tradeService;

    public StockServiceImpl(StockRepository stockRepository, TradeService tradeService) {
        this.stockRepository = stockRepository;
        this.tradeService = tradeService;
    }

    @Override
    public Double calculateDividendYield(Double price, String symbol) throws StockMarketException {
        if(price<=0d){
            throw new StockMarketException("Price is not valid");
        }
        final Stock stock=stockRepository.findBySymbol(symbol).orElseThrow(()->new StockMarketException("Stock not found"));
        return getDividend(stock)/price;
    }

    @Override
    public Double getPERatio(Double price, String symbol) throws StockMarketException {
        if (price <= 0d) {
            throw new StockMarketException("Price is not valid");
        }

        final Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new StockMarketException("Stock not found for Symbol"));

        final Double dividend = this.getDividend(stock);

        if (dividend > 0) {
            return price / dividend;
        }

        return 0d;
    }

    @Override
    public Double getVolumeWeightedStock(String symbol) throws StockMarketException {
        final Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new StockMarketException("Stock not found for Symbol"));

        final List<Trade> validTrades = tradeService.getLatestTrades(stock);
        int totalQuantity = 0;
        double tradedValue = 0d;

        if (validTrades.size() > 0) {
            for (final Trade trade : validTrades) {
                tradedValue = tradedValue + trade.getQuantity() * trade.getTradePrice();
                totalQuantity = totalQuantity + trade.getQuantity();
            }

            return tradedValue / totalQuantity;
        }

        return 0d;

    }

    @Override
    public Double calculateGeometricMean() {

        final Set<Stock> listOfStocks=stockRepository.getAllStocks();
        final List<Trade> allTrades=listOfStocks.stream()
                .map(stockRepository::getTrades).flatMap(List::stream)
                .toList();

        if (allTrades.size() > 0) {
            final Double priceMultiplier = allTrades.stream()
                    .map(Trade::getTradePrice)
                    .reduce(1d, (tradePrice1, tradePrice2) -> tradePrice1 * tradePrice2);

            return Math.pow(priceMultiplier, 1 /allTrades.size());
        }

        return 0d;
    }
    private Double getDividend(final Stock stock) {
        if (stock.getType() == StockType.COMMON) {
            return stock.getLastDividend();
        }

        return (stock.getFixedDividend() / 100) * stock.getPerValue();
    }
}
