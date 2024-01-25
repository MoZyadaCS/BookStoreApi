package com.bookstore.bookstoreapi.repositories;

import com.bookstore.bookstoreapi.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository underTest;


    @Test
    void shouldSaveUserSuccessfully(){
        // given
        Author author = Author.builder().firstName("Mostafa").lastName("Zyada").build();

        // when
        author = underTest.save(author);
        // then
        Author finalAuthor = author;

        assertAll(
                () -> assertThat(finalAuthor).isNotNull(),
                () -> assertThat(finalAuthor.getId()).isGreaterThan(0)
        );
    }
}
