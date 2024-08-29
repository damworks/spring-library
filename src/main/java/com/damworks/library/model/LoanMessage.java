package com.damworks.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "loan_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private String messageContent;

    private Date sentDate;
}
