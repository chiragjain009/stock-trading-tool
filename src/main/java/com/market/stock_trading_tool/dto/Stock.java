package com.market.stock_trading_tool.dto;

import lombok.Data;

@Data
public class Stock {
    private String symbol;
    private StockType type;
    private Double fixedDividend;
    private Double lastDividend;
    private Integer perValue;

    public Stock(final String symbol,final String type,final String fixedDividend,final String lastDividend,final String perValue) {
        this.symbol = symbol;
        this.type = StockType.valueOf(type);
        this.fixedDividend =Double.valueOf(fixedDividend);
        this.lastDividend = Double.valueOf(lastDividend);
        this.perValue = Integer.valueOf(perValue);
    }


}
