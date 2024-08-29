package com.damworks.library.service;

import com.damworks.library.model.Copy;
import com.damworks.library.model.Loan;
import com.damworks.library.model.User;
import com.damworks.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getLoansByUser(User user) {
        return loanRepository.findByUser(user);
    }

    public List<Loan> getLoansByCopy(Copy copy) {
        return loanRepository.findByCopy(copy);
    }

    public List<Loan> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
