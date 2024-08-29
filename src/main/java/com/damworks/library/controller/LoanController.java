package com.damworks.library.controller;

import com.damworks.library.model.Copy;
import com.damworks.library.model.Loan;
import com.damworks.library.model.User;
import com.damworks.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Loan> getLoansByUser(@PathVariable Long userId) {
        User user = new User();
        user.setUserId(userId);
        return loanService.getLoansByUser(user);
    }

    @GetMapping("/copy/{copyId}")
    public List<Loan> getLoansByCopy(@PathVariable Long copyId) {
        Copy copy = new Copy();
        copy.setCopyId(copyId);
        return loanService.getLoansByCopy(copy);
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan createdLoan = loanService.createLoan(loan);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        Optional<Loan> loanOptional = loanService.getLoanById(id);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setLoanDate(loanDetails.getLoanDate());
            loan.setDueDate(loanDetails.getDueDate());
            loan.setReturnDate(loanDetails.getReturnDate());
            loan.setStatus(loanDetails.getStatus());
            loanService.updateLoan(loan);
            return ResponseEntity.ok(loan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
