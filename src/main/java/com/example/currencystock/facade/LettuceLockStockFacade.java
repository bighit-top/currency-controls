package com.example.currencystock.facade;

import com.example.currencystock.repository.RedisLockRepository;
import com.example.currencystock.service.StockService;
import org.springframework.stereotype.Component;

/**
 * redis
 * lettuce 라이브러리 활용 동시성 제어 (spring data jpa 기본 세팅)
 */
@Component
public class LettuceLockStockFacade {

    private RedisLockRepository redisLockRepository;

    private StockService stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long key, Long quantity) throws InterruptedException {
        // lock 획득
        while (!redisLockRepository.lock(key)) {
            Thread.sleep(100);
        }

        try {
            stockService.decrease(key, quantity);
        } finally {
            // lock 반환
            redisLockRepository.unlock(key);
        }
    }
}
