package com.damworks.library.repository;

import com.damworks.library.model.Copy;
import com.damworks.library.model.Loan;
import com.damworks.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser(User user);
    List<Loan> findByCopy(Copy copy);
    List<Loan> findByStatus(String status);
}
