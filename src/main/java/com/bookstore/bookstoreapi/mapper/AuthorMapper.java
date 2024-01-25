package com.bookstore.bookstoreapi.mapper;

import com.bookstore.bookstoreapi.dto.AuthorResponseDto;
import com.bookstore.bookstoreapi.dto.CreateAuthorRequestDto;
import com.bookstore.bookstoreapi.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(CreateAuthorRequestDto authorRequestDto){
        Author author = new Author();
        author.setFirstName(authorRequestDto.getFirstName());
        author.setLastName(authorRequestDto.getLastName());
        return author;
    }
    public AuthorResponseDto toResponse(Author author){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setFirstName(author.getFirstName());
        authorResponseDto.setLastName(author.getLastName());
        return authorResponseDto;
    }
}
