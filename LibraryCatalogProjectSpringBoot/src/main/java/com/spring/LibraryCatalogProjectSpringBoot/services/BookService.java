package com.spring.LibraryCatalogProjectSpringBoot.services;


import com.spring.LibraryCatalogProjectSpringBoot.models.Book;
import com.spring.LibraryCatalogProjectSpringBoot.models.Person;
import com.spring.LibraryCatalogProjectSpringBoot.repositories.BookRepository;
import com.spring.LibraryCatalogProjectSpringBoot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findBooksByPage(int page, int booksPerPage){
        return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
    }
    public List<Book> findBooksByPageAndSortByYear(int page, int booksPerPage){
        return bookRepository.findAll(PageRequest.of(page,booksPerPage,Sort.by("year"))).getContent();
    }


    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id)   {
        bookRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return bookRepository.findById(id)
                .map(Book::getPerson);
    }
    @Transactional
    public void assign(int id, Person selectedPerson) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        book.setPerson(selectedPerson);
        book.setBorrowTime(new Date());
        bookRepository.save(book);
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setPerson(null);
            book.setBorrowTime(null);
            bookRepository.save(book);
        });
    }
    public List<Book> searchBooksByTitle(String prefix){
        return bookRepository.findByTitleStartingWithIgnoreCase(prefix);
    }
    public List<Book> searchBooksByAuthor(String prefix){
        return bookRepository.findByAuthorStartingWithIgnoreCase(prefix);
    }
    public boolean isBookOverdue(Book book) {
        if (book.getBorrowTime() != null) {
            Instant dueDate = book.getBorrowTime().toInstant().plus(Duration.ofDays(10));
            boolean isOverdue = Instant.now().isAfter(dueDate);
            return isOverdue;
        }
        return false;
    }
}
