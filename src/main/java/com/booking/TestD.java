package com.booking;

import com.booking.entity.Receipt;
import com.booking.entity.User;

import java.util.HashSet;
import java.util.Set;

public class TestD {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1l);
        user.setEmail("parth.prakhar007@gmail.com");
        user.setFirstName("Prakhar");
        user.setLastName("Upadhyay");

        Receipt receipt = new Receipt();
        receipt.setTo("London");
        receipt.setFrom("New York");
        receipt.setPrice(20d);
        receipt.setId(1l);
        Set<Receipt> receipts = new HashSet<>();
        receipts.add(receipt);

        user.setReceipts(receipts);

        System.out.println(user);

    }
}
