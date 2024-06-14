package com.booking.mapper;

import com.booking.dto.ReceiptDto;
import com.booking.entity.Receipt;
import com.booking.entity.User;
import com.booking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension .class)
class ReceiptMapperTest {

    @InjectMocks
    private ReceiptMapper receiptMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void testFromReceiptDto() throws Exception {
        User user = new User();
        user.setId(1L);
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setUserId(1l);

        when(userRepository.getById(anyLong())).thenReturn(user);

        Receipt receipt = receiptMapper.fromReceiptDto(receiptDto);

        assertNotNull(receipt);
        assertNotNull(receipt.getUser());

    }

    @Test
    void testFromReceiptDtoUserNotFound() {
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setUserId(2l);

        when(userRepository.getById(anyLong())).thenReturn(null);

        assertThrows(Exception.class, () -> {
            Receipt receipt = receiptMapper.fromReceiptDto(receiptDto);
        });
    }
}
