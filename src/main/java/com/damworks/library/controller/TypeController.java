package com.damworks.library.controller;

import com.damworks.library.model.Type;
import com.damworks.library.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping
    public List<Type> getAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        Optional<Type> type = typeService.getTypeById(id);
        return type.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        Type createdType = typeService.createType(type);
        return new ResponseEntity<>(createdType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type typeDetails) {
        Optional<Type> typeOptional = typeService.getTypeById(id);
        if (typeOptional.isPresent()) {
            Type type = typeOptional.get();
            type.setTypeName(typeDetails.getTypeName());
            typeService.updateType(type);
            return ResponseEntity.ok(type);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
