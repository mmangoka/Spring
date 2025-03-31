package com.transaction.transaction.Model;


import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long transactionID;

    @Column(nullable =  false)
    private String senderAccountNumber;

    @Column(nullable = false)
    private String receiverAccountNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private LocalDateTime transactionDate = LocalDateTime.now();


}
