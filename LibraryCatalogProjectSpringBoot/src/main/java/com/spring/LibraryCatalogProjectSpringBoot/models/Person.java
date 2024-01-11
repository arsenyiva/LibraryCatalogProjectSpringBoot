package com.spring.LibraryCatalogProjectSpringBoot.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "ФИО не может быть пустым!")
    @Size(min = 2, max = 100, message = "Длина ФИО должна быть больше 2х и меньше 100 символов")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]+( [А-ЯЁ][а-яё]+)*$", message = "Введите в формате: Фамилия Имя Отчество")
    @Column(name = "name")
    private String name;
    @Min(value = 1900, message = "Год должен быть больше 1990")
    @Max(value = 2023, message = "Год должен быть меньше 2023")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person(int id, String name, int yearOfBirth, List<Book> books) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.books = books;
    }

    public Person() {
    }

    public int getId()   {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}

