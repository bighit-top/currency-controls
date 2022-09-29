package com.example.currencystock.repository;

import com.example.currencystock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * mysql
 * named lock 활용 동시성 제어
 */
public interface LockRepository extends JpaRepository<Stock, Long> { // 실무에서는 별도의 JDBC 등을 활용해야 함
    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock(String key);
}
