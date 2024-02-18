package com.market.stock_trading_tool.entity;

import com.market.stock_trading_tool.dto.StockType;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity(name = "Stock")
@Data
public class StockEntity {
    @Id
    private String symbol;
    @Enumerated(EnumType.STRING)
    private StockType type;
    private Double fixedDividend;
    private Double lastDividend;
    private Integer perValue;

    @OneToMany(mappedBy = "stock")
    private List<TradeEntity> trades;

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

    public List<TradeEntity> getTrades() {
        return trades;
    }

    public void setTrades(List<TradeEntity> trades) {
        this.trades = trades;
    }
}
