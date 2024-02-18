package com.market.stock_trading_tool.repository;

import com.market.stock_trading_tool.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepositoryWithSql extends JpaRepository<TradeEntity,String>{

}
