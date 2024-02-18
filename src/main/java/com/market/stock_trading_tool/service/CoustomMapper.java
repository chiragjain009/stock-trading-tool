package com.market.stock_trading_tool.service;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.dto.TradeType;
import com.market.stock_trading_tool.entity.StockEntity;
import com.market.stock_trading_tool.entity.TradeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoustomMapper {

    
    public StockEntity stockDTOToStockEntity(StockDTO stockDTO) {
        if ( stockDTO == null ) {
            return null;
        }

        StockEntity stockEntity = new StockEntity();

        stockEntity.setSymbol( stockDTO.getSymbol() );
        stockEntity.setType( stockDTO.getType() );
        stockEntity.setFixedDividend( stockDTO.getFixedDividend() );
        stockEntity.setLastDividend( stockDTO.getLastDividend() );
        stockEntity.setPerValue( stockDTO.getPerValue() );

        return stockEntity;
    }

    
    public StockDTO stockEntityToStockDTO(StockEntity stockEntity) {
        if ( stockEntity == null ) {
            return null;
        }

        String symbol = null;
        String type = null;
        String fixedDividend = null;
        String lastDividend = null;
        String perValue = null;

        symbol = stockEntity.getSymbol();
        if ( stockEntity.getType() != null ) {
            type = stockEntity.getType().name();
        }
        if ( stockEntity.getFixedDividend() != null ) {
            fixedDividend = String.valueOf( stockEntity.getFixedDividend() );
        }
        if ( stockEntity.getLastDividend() != null ) {
            lastDividend = String.valueOf( stockEntity.getLastDividend() );
        }
        if ( stockEntity.getPerValue() != null ) {
            perValue = String.valueOf( stockEntity.getPerValue() );
        }

        StockDTO stockDTO = new StockDTO( symbol, type, fixedDividend, lastDividend, perValue );

        return stockDTO;
    }

    
    public TradeEntity tradeDTOToTradeEntity(TradeDTO tradeDTO) {
        if ( tradeDTO == null ) {
            return null;
        }

        TradeEntity tradeEntity = new TradeEntity();

        tradeEntity.setTimeStamp( tradeDTO.getTimeStamp() );
        tradeEntity.setQuantity( tradeDTO.getQuantity() );
        tradeEntity.setTradePrice( tradeDTO.getTradePrice() );
        tradeEntity.setTradeType( tradeDTO.getTradeType() );

        return tradeEntity;
    }

    
    public TradeDTO tradeEntityToTradeDTO(TradeEntity tradeEntity) {
        if ( tradeEntity == null ) {
            return null;
        }

        Integer quantity = null;
        Double tradePrice = null;
        TradeType tradeType = null;

        quantity = tradeEntity.getQuantity();
        tradePrice = tradeEntity.getTradePrice();
        tradeType = tradeEntity.getTradeType();

        TradeDTO tradeDTO = new TradeDTO( quantity, tradePrice, tradeType );

        tradeDTO.setTimeStamp( tradeEntity.getTimeStamp() );

        return tradeDTO;
    }

    
    public List<TradeDTO> tradeEntityListToTradeDTOList(List<TradeEntity> tradeEntities) {
        if ( tradeEntities == null ) {
            return null;
        }

        List<TradeDTO> list = new ArrayList<TradeDTO>( tradeEntities.size() );
        for ( TradeEntity tradeEntity : tradeEntities ) {
            list.add( tradeEntityToTradeDTO( tradeEntity ) );
        }

        return list;
    }

    
    public List<StockDTO> stockEntityListToStockDTOList(List<StockEntity> stockEntities) {
        if ( stockEntities == null ) {
            return null;
        }

        List<StockDTO> list = new ArrayList<StockDTO>( stockEntities.size() );
        for ( StockEntity stockEntity : stockEntities ) {
            list.add( stockEntityToStockDTO( stockEntity ) );
        }

        return list;
    }
}
