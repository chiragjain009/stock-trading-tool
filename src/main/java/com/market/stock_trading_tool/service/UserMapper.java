package com.market.stock_trading_tool.service;

import com.market.stock_trading_tool.dto.StockDTO;
import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.entity.StockEntity;
import com.market.stock_trading_tool.entity.TradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    StockEntity stockDTOToStockEntity(StockDTO stockDTO);
    StockDTO stockEntityToStockDTO(StockEntity stockEntity);

    TradeEntity tradeDTOToTradeEntity(TradeDTO tradeDTO);
    TradeDTO tradeEntityToTradeDTO(TradeEntity tradeEntity);

    List<TradeDTO> tradeEntityListToTradeDTOList(List<TradeEntity> tradeEntities);
    List<StockDTO> stockEntityListToStockDTOList(List<StockEntity> stockEntities);
   // void updateFromDto(StockDTO stockDTO, @MappingTarget StockEntity stockEntity);


}
