package com.market.stock_trading_tool.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class TradeDTO {
    @JsonIgnore
    private Instant timeStamp;
    private Integer quantity;
    private Double tradePrice;
    private TradeType tradeType;


    @JsonCreator()
    public TradeDTO(@JsonProperty("quantity") final Integer quantity, @JsonProperty("tradePrice") final Double tradePrice, @JsonProperty("tradeType") final TradeType tradeType) {
        this.timeStamp = Instant.now();
        this.quantity = quantity;
        this.tradePrice = tradePrice;
        this.tradeType = tradeType;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }
}
