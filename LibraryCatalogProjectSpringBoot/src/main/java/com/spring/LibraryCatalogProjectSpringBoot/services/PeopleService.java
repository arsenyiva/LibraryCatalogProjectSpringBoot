package com.spring.LibraryCatalogProjectSpringBoot.services;


import com.spring.LibraryCatalogProjectSpringBoot.models.Book;
import com.spring.LibraryCatalogProjectSpringBoot.models.Person;
import com.spring.LibraryCatalogProjectSpringBoot.repositories.BookRepository;
import com.spring.LibraryCatalogProjectSpringBoot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
    public List<Book> getBooksByPersonId(int personId) {
        return bookRepository.findByPersonId(personId);
    }
    public Optional<Person> findOneByName(String name) {
        return peopleRepository.findByName(name);
    }

}
