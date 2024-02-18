package com.market.stock_trading_tool.service.impl;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.StockType;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.exception.StockMarketException;
import com.market.stock_trading_tool.repository.StockRepositoryWithSql;
import com.market.stock_trading_tool.service.CoustomMapper;
import com.market.stock_trading_tool.service.StockService;
import com.market.stock_trading_tool.service.TradeService;
import com.market.stock_trading_tool.service.UserMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StockServiceImplWithSql implements StockService {
    private StockRepositoryWithSql stockRepository;
    private TradeService tradeService;
    private CoustomMapper userMapper;

    public StockServiceImplWithSql(StockRepositoryWithSql stockRepository, CoustomMapper userMapper, @Qualifier("tradeServiceImplWithSql") TradeService tradeService) {
        this.stockRepository = stockRepository;
        this.tradeService = tradeService;
        this.userMapper = userMapper;
    }

    @Override
    public Double calculateDividendYield(Double price, String symbol) throws StockMarketException {
        if(price<=0d){
            throw new StockMarketException("Price is not valid");
        }

        final StockDTO stock=userMapper.stockEntityToStockDTO(stockRepository.getById(symbol));
        if(stock==null)throw new StockMarketException("Stock not found");
        return getDividend(stock)/price;
    }

    @Override
    public Double getPERatio(Double price, String symbol) throws StockMarketException {
        if (price <= 0d) {
            throw new StockMarketException("Price is not valid");
        }
        final StockDTO stock=userMapper.stockEntityToStockDTO(stockRepository.getById(symbol));
        if(stock==null)throw new StockMarketException("Stock not found for Symbol");

        final Double dividend = this.getDividend(stock);

        if (dividend > 0) {
            return price / dividend;
        }

        return 0d;
    }

    @Override
    public Double getVolumeWeightedStock(String symbol) throws StockMarketException {
        final StockDTO stock=userMapper.stockEntityToStockDTO(stockRepository.getById(symbol));
        if(stock==null)throw new StockMarketException("Stock not found for Symbol");

        final List<TradeDTO> validTrades = tradeService.getLatestTrades(stock);
        int totalQuantity = 0;
        double tradedValue = 0d;

        if (validTrades.size() > 0) {
            for (final TradeDTO trade : validTrades) {
                tradedValue = tradedValue + trade.getQuantity() * trade.getTradePrice();
                totalQuantity = totalQuantity + trade.getQuantity();
            }

            return tradedValue / totalQuantity;
        }

        return 0d;

    }

    @Override
    public Double calculateGeometricMean() {

        final List<StockDTO> listOfStocks=userMapper.stockEntityListToStockDTOList(stockRepository.findAll());

        final List<TradeDTO> allTrades=listOfStocks.stream()
                .map(tradeService::getTrades).flatMap(List::stream)
                .toList();

        if (allTrades.size() > 0) {
            final Double priceMultiplier = allTrades.stream()
                    .map(TradeDTO::getTradePrice)
                    .reduce(1d, (tradePrice1, tradePrice2) -> tradePrice1 * tradePrice2);

            return Math.pow(priceMultiplier, 1 /allTrades.size());
        }

        return 0d;
    }
    private Double getDividend(final StockDTO stock) {
        if (stock.getType() == StockType.COMMON) {
            return stock.getLastDividend();
        }

        return (stock.getFixedDividend() / 100) * stock.getPerValue();
    }
}
