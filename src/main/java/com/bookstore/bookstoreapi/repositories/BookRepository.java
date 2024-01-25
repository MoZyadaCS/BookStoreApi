package com.bookstore.bookstoreapi.repositories;

import com.bookstore.bookstoreapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
