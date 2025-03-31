package com.transaction.transaction.payLoad;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransactionsDTO {

        private long transactionID;
        private String senderAccountNumber;
        private String receiverAccountNumber;
        private BigDecimal amount;
        private String transactionType;
        private LocalDateTime transactionDate = LocalDateTime.now();


}
