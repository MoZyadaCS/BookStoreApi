package com.bookstore.bookstoreapi.service.impl;

import com.bookstore.bookstoreapi.dto.AuthorResponseDto;
import com.bookstore.bookstoreapi.dto.CreateAuthorRequestDto;
import com.bookstore.bookstoreapi.dto.EditAuthorDto;
import com.bookstore.bookstoreapi.entity.Author;
import com.bookstore.bookstoreapi.exception.NoAuthorFoundException;
import com.bookstore.bookstoreapi.mapper.AuthorMapper;
import com.bookstore.bookstoreapi.repositories.AuthorRepository;
import com.bookstore.bookstoreapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public AuthorResponseDto createAuthor(CreateAuthorRequestDto authorRequestDto) {
        Author author = this.authorMapper.toEntity(authorRequestDto);
        author = this.authorRepository.save(author);
        return this.authorMapper.toResponse(author);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return this.authorRepository.findAll()
                .stream()
                .map(author -> this.authorMapper.toResponse(author))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        return this.authorMapper
                .toResponse(
                        this.authorRepository.findById(id)
                                .orElseThrow(() ->
                                        new NoAuthorFoundException("No Author Found with id " + id)
                                ));
    }

    @Override
    public AuthorResponseDto editAuthor(Long id, EditAuthorDto editAuthorDto) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()) throw new NoAuthorFoundException("No Author Found with id " + id);
        Author author = authorOptional.get();
        mapEditAuthorDetails(editAuthorDto,author);
        author = this.authorRepository.save(author);
        return authorMapper.toResponse(author);

    }

    private void mapEditAuthorDetails(EditAuthorDto editAuthorDto, Author author) {
        author.setFirstName(editAuthorDto.getFirstName());
        author.setLastName(editAuthorDto.getLastName());
    }


}
