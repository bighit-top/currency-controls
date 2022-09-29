package com.example.currencystock.service;

import com.example.currencystock.domain.Stock;
import com.example.currencystock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * mysql
 * pessimistic lock 활용 동시성 제어
 */
@Service
public class PessimisticLockStockService {

    private StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        // lock 을 걸고 데이터를 가져옴
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);

        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
