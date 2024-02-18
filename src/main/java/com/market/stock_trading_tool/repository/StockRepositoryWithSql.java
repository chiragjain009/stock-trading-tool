package com.market.stock_trading_tool.repository;

import com.market.stock_trading_tool.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepositoryWithSql extends JpaRepository<StockEntity,String>{
}
