package com.booking.dto;

import com.booking.enumm.Section;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptDto {
    private String from;
    private String to;
    private Section section;
    private Double price;
    private int seatNo;
    private Long userId;
}
