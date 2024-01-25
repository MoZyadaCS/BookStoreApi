package com.bookstore.bookstoreapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class BookEntityTest {


    private Book underTest;


    @BeforeEach
    void setUp(){
        this.underTest = new Book();
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
    void shouldGetTitle() throws NoSuchFieldException, IllegalAccessException {
        //given
        String title = "Book title";

        // when
        final Field field = this.underTest.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(underTest,title);

        // then
        assertEquals(title,underTest.getTitle());
    }

    @Test
    void shouldGetPrice() throws NoSuchFieldException, IllegalAccessException {
        //given
        Double price = 125.2;

        // when
        final Field field = underTest.getClass().getDeclaredField("price");
        field.setAccessible(true);
        field.set(underTest,price);

        // then
        assertEquals(price,underTest.getPrice());
    }

    @Test
    void shouldGetQuantity() throws NoSuchFieldException, IllegalAccessException {
        // given
        Integer quantity = 10;

        // when
        final Field field = underTest.getClass().getDeclaredField("quantity");
        field.setAccessible(true);
        field.set(underTest,quantity);

        // then
        assertEquals(quantity,field.get(underTest));
    }

    @Test
    void shouldGetAuthor() throws NoSuchFieldException, IllegalAccessException {
        // given
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("Mostafa");
        author.setLastName("Zyada");

        // when
        final Field field = underTest.getClass().getDeclaredField("author");
        field.setAccessible(true);
        field.set(underTest,author);

        // then
        assertEquals(underTest.getAuthor(),author);
    }

    @Test
    void shouldSetTitle() throws NoSuchFieldException, IllegalAccessException {
        // given
        String title = "Book title";

        // when
        final Field field = underTest.getClass().getDeclaredField("title");
        field.setAccessible(true);
        underTest.setTitle(title);

        // then
        assertEquals(title,field.get(underTest));
    }

    @Test
    void shouldSetPrice() throws NoSuchFieldException, IllegalAccessException {
        // given
        Double price = 125.15;

        // when
        final Field field = underTest.getClass().getDeclaredField("price");
        field.setAccessible(true);
        underTest.setPrice(price);

        // then
        assertEquals(price,field.get(underTest));
    }

    @Test
    void shouldSetQuantity() throws NoSuchFieldException, IllegalAccessException {
        // given
        Integer quantity = 10;

        // when
        final Field field = underTest.getClass().getDeclaredField("quantity");
        field.setAccessible(true);
        underTest.setQuantity(quantity);

        // then
        assertEquals(quantity,field.get(underTest));
    }

    @Test
    void shouldSetAuthor() throws NoSuchFieldException, IllegalAccessException {
        // given
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("Mostafa");
        author.setLastName("Zyada");

        // when
        final Field field = underTest.getClass().getDeclaredField("author");
        field.setAccessible(true);
        underTest.setAuthor(author);
        // then
        assertEquals(author,field.get(underTest));
    }
}