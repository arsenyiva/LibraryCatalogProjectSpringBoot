package com.spring.LibraryCatalogProjectSpringBoot.controllers;


import com.spring.LibraryCatalogProjectSpringBoot.models.Book;
import com.spring.LibraryCatalogProjectSpringBoot.models.Person;
import com.spring.LibraryCatalogProjectSpringBoot.services.BookService;
import com.spring.LibraryCatalogProjectSpringBoot.services.PeopleService;
import com.spring.LibraryCatalogProjectSpringBoot.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService, BookService bookService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Person person = peopleService.findOne(id);
        List<Book> books = peopleService.getBooksByPersonId(id);

        model.addAttribute("person", person);
        model.addAttribute("books", books);

        Map<Integer, Boolean> bookOverdueMap = new HashMap<>();
        for (Book book : books) {
            bookOverdueMap.put(book.getId(), bookService.isBookOverdue(book));
        }
        model.addAttribute("bookOverdueMap", bookOverdueMap);

        return "people/show";
    }


    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
