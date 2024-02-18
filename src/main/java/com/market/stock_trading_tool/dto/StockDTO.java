package com.market.stock_trading_tool.dto;

import lombok.Data;

@Data
public class StockDTO {
    private String symbol;
    private StockType type;
    private Double fixedDividend;
    private Double lastDividend;
    private Integer perValue;

    public StockDTO(final String symbol, final String type, final String fixedDividend, final String lastDividend, final String perValue) {
        this.symbol = symbol;
        this.type = StockType.valueOf(type);
        this.fixedDividend =Double.valueOf(fixedDividend);
        this.lastDividend = Double.valueOf(lastDividend);
        this.perValue = Integer.valueOf(perValue);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockType getType() {
        return type;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Integer getPerValue() {
        return perValue;
    }

    public void setPerValue(Integer perValue) {
        this.perValue = perValue;
    }
}
