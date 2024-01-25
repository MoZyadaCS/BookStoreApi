package com.bookstore.bookstoreapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorEntityTest {

    private Author underTest;


    @BeforeEach
    void setUp(){
        this.underTest = new Author();
    }

    @Test
    void shouldSetId() throws NoSuchFieldException, IllegalAccessException {
        //given
        Long id = 1L;

        //when
        this.underTest.setId(id);
        final Field field = underTest.getClass().getDeclaredField("id");
        field.setAccessible(true);

        //then
        assertEquals(id, field.get(underTest));
    }
    @Test
    void shouldGetId() throws NoSuchFieldException, IllegalAccessException {
        // given
        Long id = 1L;

        // when
        final Field field = underTest.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(underTest,id);

        // then
        assertEquals(id,field.get(underTest));
    }

    @Test
    void shouldGetFirstName() throws NoSuchFieldException, IllegalAccessException {
        // given
        String firstName = "Mostafa";

        // when
        final Field field = underTest.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(underTest,firstName);

        // then
        assertEquals(firstName,underTest.getFirstName());
    }

    @Test
    void shouldSetFirstName() throws NoSuchFieldException, IllegalAccessException {
        // given
        String firstName = "Mostafa";

        // when
        final Field field = underTest.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        underTest.setFirstName(firstName);
        // then
        assertEquals(firstName,field.get(underTest));
    }

    @Test
    void shouldGetLastName() throws NoSuchFieldException, IllegalAccessException {
        // given
        String lastName = "Zyada";

        // when
        final Field field = underTest.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(underTest,lastName);

        // then
        assertEquals(lastName,underTest.getLastName());
    }

    @Test
    void shouldSetLastName() throws NoSuchFieldException, IllegalAccessException {
        // given
        String lastName = "Zyada";

        // when
        final Field field = underTest.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        underTest.setLastName(lastName);

        // then
        assertEquals(lastName,field.get(underTest));
    }

    @Test
    void shouldGetBooks() throws NoSuchFieldException, IllegalAccessException {
        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book());

        // when
        final Field field = underTest.getClass().getDeclaredField("books");
        field.setAccessible(true);
        field.set(underTest,books);

        // then
        assertEquals(books,underTest.getBooks());
    }

    @Test
    void shouldSetBooks() throws NoSuchFieldException, IllegalAccessException {
        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book());

        // when
        final Field field = underTest.getClass().getDeclaredField("books");
        field.setAccessible(true);
        underTest.setBooks(books);

        // then
        assertEquals(books,field.get(underTest));
    }

}