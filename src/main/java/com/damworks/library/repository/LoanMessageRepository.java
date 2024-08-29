package com.damworks.library.repository;

import com.damworks.library.model.LoanMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanMessageRepository extends JpaRepository<LoanMessage, Long> {
}
