package com.bookstore.bookstoreapi.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthorResponseDto {
    private Long id;
    private String firstName;

    private String lastName;
}
