package com.bookstore.bookstoreapi.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    private Long id;

    private String title;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
