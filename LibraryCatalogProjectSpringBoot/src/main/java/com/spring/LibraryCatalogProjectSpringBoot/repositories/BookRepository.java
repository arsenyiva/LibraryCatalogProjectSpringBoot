package com.spring.LibraryCatalogProjectSpringBoot.repositories;

import com.spring.LibraryCatalogProjectSpringBoot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByPersonId(int personId);
    List<Book> findByTitleStartingWithIgnoreCase(String prefix);

    List<Book> findByAuthorStartingWithIgnoreCase(String prefix);
}