package com.damworks.library.repository;

import com.damworks.library.model.Book;
import com.damworks.library.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Long> {
    List<Copy> findByBook(Book book);
}
