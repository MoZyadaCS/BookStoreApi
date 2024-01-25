package com.bookstore.bookstoreapi.controller;


import com.bookstore.bookstoreapi.dto.AuthorResponseDto;
import com.bookstore.bookstoreapi.dto.CreateAuthorRequestDto;
import com.bookstore.bookstoreapi.dto.EditAuthorDto;
import com.bookstore.bookstoreapi.service.AuthorService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {

    private final AuthorService authorService;
    
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody CreateAuthorRequestDto authorRequestDto){
        return new ResponseEntity<>(this.authorService.createAuthor(authorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors(){
        return new ResponseEntity<>(this.authorService.getAllAuthors(),HttpStatus.OK);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable("authorId") Long authorId){
        return new ResponseEntity<>(this.authorService.getAuthorById(authorId),HttpStatus.OK);
    }

    @PutMapping("{authorId}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable("authorId") Long authorId ,@Valid @RequestBody EditAuthorDto editAuthorDto){
        return new ResponseEntity<>(this.authorService.editAuthor(authorId,editAuthorDto),HttpStatus.OK);
    }

}
