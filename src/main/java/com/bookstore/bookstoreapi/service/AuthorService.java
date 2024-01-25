package com.bookstore.bookstoreapi.service;

import com.bookstore.bookstoreapi.dto.AuthorResponseDto;
import com.bookstore.bookstoreapi.dto.CreateAuthorRequestDto;
import com.bookstore.bookstoreapi.dto.EditAuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto createAuthor(CreateAuthorRequestDto authorRequestDto);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto getAuthorById(Long id);

    AuthorResponseDto editAuthor(Long id , EditAuthorDto editAuthorDto);
}
