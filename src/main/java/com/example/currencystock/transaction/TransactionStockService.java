package com.example.currencystock.transaction;

import com.example.currencystock.service.StockService;

/**
 * @Transaction 동작 원리의 예
 */
public class TransactionStockService {
    
    private StockService stockService;

    public TransactionStockService(StockService stockService) {
        this.stockService = stockService;
    }
    
    public void decrease(Long id, Long quantity) {
        startTransaction();
        stockService.decrease(id, quantity);
        endTransaction();
    }

    private void endTransaction() {
    }

    private void startTransaction() {
    }
}
