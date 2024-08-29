package com.damworks.library.service;

import com.damworks.library.model.LoanMessage;
import com.damworks.library.repository.LoanMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanMessageService {
    @Autowired
    private LoanMessageRepository loanMessageRepository;

    public List<LoanMessage> getAllLoanMessages() {
        return loanMessageRepository.findAll();
    }

    public Optional<LoanMessage> getLoanMessageById(Long id) {
        return loanMessageRepository.findById(id);
    }

    public LoanMessage createLoanMessage(LoanMessage loanMessage) {
        return loanMessageRepository.save(loanMessage);
    }

    public LoanMessage updateLoanMessage(LoanMessage loanMessage) {
        return loanMessageRepository.save(loanMessage);
    }

    public void deleteLoanMessage(Long id) {
        loanMessageRepository.deleteById(id);
    }
}
