package com.bookstore.bookstoreapi.service;

import com.bookstore.bookstoreapi.dto.AuthorResponseDto;
import com.bookstore.bookstoreapi.dto.CreateAuthorRequestDto;
import com.bookstore.bookstoreapi.dto.EditAuthorDto;
import com.bookstore.bookstoreapi.entity.Author;
import com.bookstore.bookstoreapi.exception.NoAuthorFoundException;
import com.bookstore.bookstoreapi.mapper.AuthorMapper;
import com.bookstore.bookstoreapi.repositories.AuthorRepository;
import com.bookstore.bookstoreapi.service.impl.AuthorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl underTest;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;


    @Test
    void shouldCreateAuthorSuccessfully() {
        // given
        String firstName = "Mostafa";
        String lastName = "Zyada";
        Long id = 1L;
        CreateAuthorRequestDto requestDto = new CreateAuthorRequestDto();
        requestDto.setFirstName(firstName);
        requestDto.setLastName(lastName);
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(id);
        authorResponseDto.setFirstName(firstName);
        authorResponseDto.setLastName(lastName);

        ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
        when(authorMapper.toEntity(ArgumentMatchers.any())).thenReturn(author);
        when(authorMapper.toResponse(ArgumentMatchers.any())).thenReturn(authorResponseDto);
        when(authorRepository.save(author)).thenReturn(author);

        // when
        AuthorResponseDto responseDto = underTest.createAuthor(requestDto);
        // then
        assertAll(() -> assertThat(responseDto.getFirstName()).isEqualTo(firstName),
                  () -> assertThat(responseDto.getLastName()).isEqualTo(lastName),
                  () -> assertThat(responseDto.getId()).isEqualTo(id),
                  () ->verify(authorRepository).save(authorArgumentCaptor.capture()),
                  () -> verify(authorMapper).toEntity(any()),
                  () -> verify(authorMapper).toResponse(any())
        );



    }

    @Test
    void shouldGetAllAuthorsSuccessfully(){
        // given
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        authors.add(author);
        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.toResponse(any())).thenReturn(new AuthorResponseDto());

        // when
        List<AuthorResponseDto> response = underTest.getAllAuthors();
        // then
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void shouldGetAuthorByIdSuccessfully(){
        // given
        Long id = 1L;
        Author author = new Author();
        author.setId(id);
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setId(id);
        when(authorMapper.toResponse(author)).thenReturn(responseDto);
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        // when
        AuthorResponseDto response = underTest.getAuthorById(id);
        // then
        assertAll(
                () -> assertThat(response.getId()).isEqualTo(id),
                () -> verify(authorMapper).toResponse(author)
        );
    }

    @Test
    void shouldThrowExceptionWhenAuthorNotFound(){
        // given
        Long id = 1l;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        // when
        NoAuthorFoundException exception = assertThrows(
                NoAuthorFoundException.class,
                () -> underTest.getAuthorById(id)
        );
        // then
        assertThat(exception.getMessage()).isEqualTo("No Author Found with id " + id);

        // another way of testing if for example we don't want to couple the test with the error message
        assertThrows(NoAuthorFoundException.class,
                () -> underTest.getAuthorById(id));
    }


    @Test
    void shouldEditAuthorSuccessfully(){
        // given
        Long id = 1L;
        Author author = Author.builder().id(id).firstName("Mostafa").lastName("Zyada").build();
        EditAuthorDto editAuthorDto = EditAuthorDto.builder().firstName("Mahmoud").lastName("Ahmed").build();
        AuthorResponseDto response = AuthorResponseDto.builder().id(id).firstName("Mahmoud").lastName("Ahmed").build();
        Author editedAuthor = Author.builder().id(id).firstName("Mahmoud").lastName("Ahmed").build();
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        when(authorMapper.toResponse(editedAuthor)).thenReturn(response);
        when(authorRepository.save(any())).thenReturn(editedAuthor);
        // when
        AuthorResponseDto responseDto = underTest.editAuthor(id,editAuthorDto);

        // then
        assertAll(
                () -> assertNotNull(responseDto),
                () -> assertThat(responseDto.getFirstName()).isEqualTo("Mahmoud"),
                () -> assertThat(responseDto.getLastName()).isEqualTo("Ahmed")
        );
    }

    @Test
    void shouldFailToEditAuthorWhenAuthorDoesNotExist(){
        // given
        Long id = 1L;
        EditAuthorDto editAuthorDto = EditAuthorDto.builder().firstName("Mahmoud").lastName("Ahmed").build();
        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        // when
        NoAuthorFoundException exception = assertThrows(
                NoAuthorFoundException.class,
                () -> underTest.editAuthor(id,editAuthorDto)
        );

        // then
        assertAll(
                () -> assertThrows(NoAuthorFoundException.class,
                        () -> underTest.editAuthor(id,editAuthorDto)),
                () -> assertThat(exception.getMessage()).isEqualTo("No Author Found with id " + id)
        );

    }
}