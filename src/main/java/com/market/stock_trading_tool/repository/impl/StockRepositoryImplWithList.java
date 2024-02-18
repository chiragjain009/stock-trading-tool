package com.market.stock_trading_tool.repository.impl;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.exception.TradeException;
import com.market.stock_trading_tool.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class StockRepositoryImplWithList implements StockRepository {

    private Map<StockDTO, List<TradeDTO>> tradeMap;

    @Autowired
    public StockRepositoryImplWithList(@Value("${initial.stocks}") final String stocks) {
        this.tradeMap = new ConcurrentHashMap<>();
        initialStocksSetup(stocks);
    }

    private void initialStocksSetup(String stocks) {
        final String[] listOfStocks=stocks.split("\\|");
        for(String stock:listOfStocks){
            final String[] stockData = stock.split(",");
            final StockDTO stockDto = new StockDTO(stockData[0], stockData[1], stockData[3], stockData[2], stockData[4]);
            this.addStock(stockDto);
        }

    }

    @Override
    public StockDTO addStock(StockDTO stock) {
        tradeMap.put(stock,new ArrayList<>());

        return stock;
    }

    @Override
    public Optional<StockDTO> findBySymbol(String symbol) {
        return tradeMap.keySet().stream().filter(stock-> stock.getSymbol().equals(symbol)).findFirst();
    }

    @Override
    public Set<StockDTO> getAllStocks() {
        return tradeMap.keySet();
    }

    @Override
    public boolean addTrade(String symbol, TradeDTO trade) throws TradeException {
        final StockDTO tradedStock = this.findBySymbol(symbol)
                .orElseThrow(() -> new TradeException("Stock not found"));

        List<TradeDTO> listOfTrade=tradeMap.getOrDefault(tradedStock,null);
        return listOfTrade.add(trade);

    }

    @Override
    public List<TradeDTO> getTrades(StockDTO stock) {
        return Optional.ofNullable(tradeMap.get(stock)).orElse(Collections.emptyList());
    }
}
