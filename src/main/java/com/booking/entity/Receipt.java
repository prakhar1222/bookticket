package com.booking.entity;

import com.booking.enumm.Section;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "receipts")
@Getter
@Setter
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_generator")
    private Long id;

    @Column(name = "from_city")
    private String from;

    @Column(name = "to_city")
    private String to;

    @Column(name = "seatno")
    private Integer seatno;

    @Column(name = "price")
    private Double price;

    @Column(name = "section")
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "myuser_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


}