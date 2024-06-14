package com.booking.controller;

import com.booking.dto.ReceiptDto;
import com.booking.dto.Response;
import com.booking.entity.Receipt;
import com.booking.entity.User;
import com.booking.mapper.ReceiptMapper;
import com.booking.repository.ReceiptRepository;
import com.booking.repository.UserRepository;
import com.booking.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "receipt")
public class ReceiptController {
    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    ReceiptService receiptService;

    @Autowired
    ReceiptMapper receiptMapper;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Response> purchaseTicket(@RequestBody ReceiptDto receiptDto) throws Exception {
        Receipt receipt = receiptMapper.fromReceiptDto(receiptDto);
        receiptRepository.save(receipt);
        return ResponseEntity.ok().body(generateResponse("Successfully added added!!",
                HttpStatus.OK.name(), null));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Receipt> getReceiptDetails(@PathVariable Long userId) {
        Receipt receipt = receiptRepository.findById(userId).orElse(null);
        return ResponseEntity.ok(receipt);
    }

    @PutMapping("/{userId}/receipts/{receiptId}")
    public ResponseEntity<Receipt> updateUserSeat(@PathVariable Long userId, @PathVariable Long receiptId, @RequestBody Integer newSeatNo) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()){
            Optional<Receipt> receiptOptional = receiptRepository.findById(receiptId);
            if(receiptOptional.isPresent()){
                Receipt receipt = receiptOptional.get();
                if(receipt.getUser().getId().equals(userId)){
                    receipt.setSeatno(newSeatNo);
                    receiptRepository.save(receipt);

                    return ResponseEntity.ok(receipt);
                }
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/receipts")
    public void removeUserFromReceipt(@PathVariable Long id) {
        receiptService.removeUserFromReceipt(id);
    }

    private Response generateResponse(String message, String status, Object responseData) {
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(status);
        response.setResponseData(responseData);

        return response;
    }


}
