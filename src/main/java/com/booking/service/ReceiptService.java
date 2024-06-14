package com.booking.service;

import com.booking.repository.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    @Transactional
    public void removeUserFromReceipt(Long userId) {
        receiptRepository.removeByUserId(userId);
    }
}
