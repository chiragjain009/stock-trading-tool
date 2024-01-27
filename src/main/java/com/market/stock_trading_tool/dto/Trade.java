package com.market.stock_trading_tool.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class Trade {
    @JsonIgnore
    private Instant timeStamp;
    private Integer quantity;
    private Double tradePrice;
    private TradeType tradeType;


    @JsonCreator()
    public Trade(@JsonProperty("quantity") final Integer quantity,@JsonProperty("tradePrice") final Double tradePrice,@JsonProperty("tradeType") final TradeType tradeType) {
        this.timeStamp = Instant.now();
        this.quantity = quantity;
        this.tradePrice = tradePrice;
        this.tradeType = tradeType;
    }

}
