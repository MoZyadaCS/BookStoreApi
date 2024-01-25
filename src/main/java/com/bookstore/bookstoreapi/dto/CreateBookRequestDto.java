package com.bookstore.bookstoreapi.dto;

import com.bookstore.bookstoreapi.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDto {

    private String title;

    private Double price;

    private Integer quantity;

    private Author author;
}
