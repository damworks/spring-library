package com.damworks.library.service;

import com.damworks.library.model.Book;
import com.damworks.library.model.Copy;
import com.damworks.library.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyService {
    @Autowired
    private CopyRepository copyRepository;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Optional<Copy> getCopyById(Long id) {
        return copyRepository.findById(id);
    }

    public List<Copy> getCopiesByBook(Book book) {
        return copyRepository.findByBook(book);
    }

    public Copy createCopy(Copy copy) {
        return copyRepository.save(copy);
    }

    public Copy updateCopy(Copy copy) {
        return copyRepository.save(copy);
    }

    public void deleteCopy(Long id) {
        copyRepository.deleteById(id);
    }
}
