package com.booking.controller;

import com.booking.dto.ReceiptDto;
import com.booking.dto.Response;
import com.booking.entity.Receipt;
import com.booking.entity.User;
import com.booking.mapper.ReceiptMapper;
import com.booking.repository.ReceiptRepository;
import com.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "receipt")
public class ReceiptController {
    @Autowired
    ReceiptRepository receiptRepository;


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


    private Response generateResponse(String message, String status, Object responseData) {
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(status);
        response.setResponseData(responseData);

        return response;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Receipt>> getAllReceiptByUserId(@PathVariable(value = "userId") Long userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }

        List<Receipt> receipts = receiptRepository.findByUserId(userId);
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @PutMapping("/{userId}/receipt")
    public ResponseEntity<Receipt> getUpdateRecipt(@PathVariable(value = "userId") Long userId, @RequestBody ReceiptDto receiptDto) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }
        if (!receiptRepository.existsById(receiptDto.getReceiptId())) {
            throw new Exception("Not found Receipt with id = " + receiptDto.getReceiptId());
        }
        User userU  = userRepository.findById(userId).get();
        Optional<Receipt> receipt = receiptRepository.findById(receiptDto.getReceiptId());
        Receipt receiptU = receipt.get();
        receiptU.setTo(receiptU.getTo());
        receiptU.setFrom(receiptU.getFrom());
        receiptU.setUser(userU);
        receiptU.setPrice(receiptDto.getPrice());
        receiptU.setSection(receiptDto.getSection());
        receiptU.setSeatno(receiptDto.getSeatNo());
        receiptRepository.save(receiptU);
        return new ResponseEntity<>(receiptU, HttpStatus.OK);
    }
}