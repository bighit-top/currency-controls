package com.example.currencystock.service;

import com.example.currencystock.domain.Stock;
import com.example.currencystock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

//    @Transactional
//    public void decrease(Long id, Long quantity) {
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 부모 트랜잭션과 별도로 실행
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
