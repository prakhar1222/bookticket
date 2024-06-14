package com.booking.repository;

import com.booking.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    void removeByUserId(Long userId);
}