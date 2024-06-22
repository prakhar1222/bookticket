package com.booking.repository;

import com.booking.entity.Receipt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


    List<Receipt> findByUserId(Long userId);

    @Transactional
    void deleteByUserId(long userId);
}