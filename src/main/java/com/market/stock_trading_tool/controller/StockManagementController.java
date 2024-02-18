package com.market.stock_trading_tool.controller;

import com.market.stock_trading_tool.dto.TradeDTO;
import com.market.stock_trading_tool.exception.StockMarketException;
import com.market.stock_trading_tool.exception.TradeException;
import com.market.stock_trading_tool.service.StockService;
import com.market.stock_trading_tool.service.TradeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping(value="/stock/")
public class StockManagementController {

    private static final Logger LOG=LogManager.getLogger(StockManagementController.class);
    private final StockService stockService;
    private final TradeService tradeService;

    public StockManagementController(@Qualifier("stockServiceImplWithSql") StockService stockService,@Qualifier("tradeServiceImplWithSql") TradeService tradeService) {
        this.stockService = stockService;
        this.tradeService = tradeService;
    }


    @PostMapping(path = "/trade/{symbol}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> stockTradeForNewTrans(@PathVariable String symbol, @RequestBody TradeDTO trade){
         try{
             tradeService.tradeStock(symbol,trade);
             return ResponseEntity.created(null).build();
         }catch (final TradeException tex){
             return ResponseEntity.badRequest().body(tex.getMessage());
         }
    }

    @GetMapping("/{symbol}/dividend-yield")
    public ResponseEntity<String> getDividendYield(@PathVariable String symbol,@RequestParam String price){
        try{
            final Double dividendYield=stockService.calculateDividendYield(Double.valueOf(price),symbol);
            return ResponseEntity.ok(String.valueOf(dividendYield));
        }catch (final StockMarketException tex){
            return ResponseEntity.badRequest().body(tex.getMessage());
        }
    }
    @GetMapping("/{symbol}/pe-ratio")
    public ResponseEntity<String> getPeRatio(@PathVariable String symbol,@RequestParam String price){
        try{
            final Double PERatio=stockService.getPERatio(Double.valueOf(price),symbol);
            return ResponseEntity.ok(String.valueOf(PERatio));
        }catch (final StockMarketException tex){
            return ResponseEntity.badRequest().body(tex.getMessage());
        }
    }

    @GetMapping("/{symbol}/volume-weight-price")
    public ResponseEntity<String> getVolumeWeightedStockPrice(@PathVariable final String symbol) {
        try{
            final Double stockPrice = stockService.getVolumeWeightedStock(symbol);
            return ResponseEntity.ok(String.valueOf(stockPrice));
        } catch (final StockMarketException sme) {
            LOG.error("Unable to get Volume Weighted Stock price for {}", symbol, sme);
            return ResponseEntity.badRequest().body(sme.getMessage());
        }
    }
    @GetMapping("/all-share-index")
    public ResponseEntity<String> getAllShareIndex() {
        final Double index = stockService.calculateGeometricMean();
        return ResponseEntity.ok(String.valueOf(index));
    }

}
