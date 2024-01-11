package com.spring.LibraryCatalogProjectSpringBoot.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;


@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Название не может быть пустым!")
    @Size(min = 1, max = 100, message = "Длина должна быть больше 1 и меньше 100 символов")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(min = 1, max = 100, message = "Длина должна быть больше 1 и меньше 100 символов")
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "borrow_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowTime;



    public Book() {
    }

    public Book(int id, String title, String author, int year, Person person, Date borrowTime) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.person = person;
        this.borrowTime = borrowTime;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }


}
