INSERT INTO Stock (symbol, type, fixed_Dividend,last_Dividend,per_Value)
VALUES ('POP','COMMON',8,5,100);

INSERT INTO trade (quantity , trade_price, time_stamp  , stock_symbol , trade_type)
VALUES (2,45,current_date(),'POP','BUY');
