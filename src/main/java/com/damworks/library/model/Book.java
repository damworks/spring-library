package com.damworks.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    private String author;

    private String isbn;

    private String publishedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = true)
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(length = 1000)
    private String notes;

    private String tags;

    @OneToMany(mappedBy = "book")
    private List<Copy> copies;
}
