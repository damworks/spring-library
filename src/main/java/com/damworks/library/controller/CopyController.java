package com.damworks.library.controller;

import com.damworks.library.model.Book;
import com.damworks.library.model.Copy;
import com.damworks.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/copies")
public class CopyController {
    @Autowired
    private CopyService copyService;

    @GetMapping
    public List<Copy> getAllCopies() {
        return copyService.getAllCopies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Copy> getCopyById(@PathVariable Long id) {
        Optional<Copy> copy = copyService.getCopyById(id);
        return copy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/book/{bookId}")
    public List<Copy> getCopiesByBook(@PathVariable Long bookId) {
        Book book = new Book();
        book.setBookId(bookId);
        return copyService.getCopiesByBook(book);
    }

    @PostMapping
    public ResponseEntity<Copy> createCopy(@RequestBody Copy copy) {
        Copy createdCopy = copyService.createCopy(copy);
        return new ResponseEntity<>(createdCopy, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Copy> updateCopy(@PathVariable Long id, @RequestBody Copy copyDetails) {
        Optional<Copy> copyOptional = copyService.getCopyById(id);
        if (copyOptional.isPresent()) {
            Copy copy = copyOptional.get();
            copy.setLibraryLocation(copyDetails.getLibraryLocation());
            copy.setStatus(copyDetails.getStatus());
            copyService.updateCopy(copy);
            return ResponseEntity.ok(copy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long id) {
        copyService.deleteCopy(id);
        return ResponseEntity.noContent().build();
    }
}
