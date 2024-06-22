package com.booking.mapper;

import com.booking.dto.ReceiptDto;
import com.booking.entity.Receipt;
import com.booking.entity.User;
import com.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiptMapper {
    @Autowired
    UserRepository userRepository;
    public  Receipt fromReceiptDto(ReceiptDto receiptDto) throws Exception {
        Receipt receipt = new Receipt();
        receipt.setFrom(receiptDto.getFrom());
        receipt.setTo(receiptDto.getTo());
        receipt.setSeatno(receiptDto.getSeatNo());
        receipt.setPrice(receiptDto.getPrice());
        receipt.setSection(receiptDto.getSection());
        Optional<User> user = userRepository.findById(receiptDto.getUserId());
        if(user.isEmpty()){
            throw new Exception("User not found");
        }
        receipt.setUser(user.get());
        return receipt;

    }
}