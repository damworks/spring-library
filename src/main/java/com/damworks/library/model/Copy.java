package com.damworks.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "copies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String libraryLocation;

    private String status;
}
