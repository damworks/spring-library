package com.damworks.library.controller;

import com.damworks.library.model.LoanMessage;
import com.damworks.library.service.LoanMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loan-messages")
public class LoanMessageController {
    @Autowired
    private LoanMessageService loanMessageService;

    @GetMapping
    public List<LoanMessage> getAllLoanMessages() {
        return loanMessageService.getAllLoanMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanMessage> getLoanMessageById(@PathVariable Long id) {
        Optional<LoanMessage> loanMessage = loanMessageService.getLoanMessageById(id);
        return loanMessage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoanMessage> createLoanMessage(@RequestBody LoanMessage loanMessage) {
        LoanMessage createdLoanMessage = loanMessageService.createLoanMessage(loanMessage);
        return new ResponseEntity<>(createdLoanMessage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanMessage> updateLoanMessage(@PathVariable Long id, @RequestBody LoanMessage loanMessageDetails) {
        Optional<LoanMessage> loanMessageOptional = loanMessageService.getLoanMessageById(id);
        if (loanMessageOptional.isPresent()) {
            LoanMessage loanMessage = loanMessageOptional.get();
            loanMessage.setMessageContent(loanMessageDetails.getMessageContent());
            loanMessage.setSentDate(loanMessageDetails.getSentDate());
            loanMessageService.updateLoanMessage(loanMessage);
            return ResponseEntity.ok(loanMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanMessage(@PathVariable Long id) {
        loanMessageService.deleteLoanMessage(id);
        return ResponseEntity.noContent().build();
    }
}
