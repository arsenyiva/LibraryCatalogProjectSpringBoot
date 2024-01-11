package com.spring.LibraryCatalogProjectSpringBoot.controllers;


import com.spring.LibraryCatalogProjectSpringBoot.models.Book;
import com.spring.LibraryCatalogProjectSpringBoot.models.Person;
import com.spring.LibraryCatalogProjectSpringBoot.services.BookService;
import com.spring.LibraryCatalogProjectSpringBoot.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;

        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "books_per_page", defaultValue = "1    0") int booksPerPage,
            @RequestParam(name = "sort_by_year", defaultValue = "false") boolean sortByYear,
            Model model
    ) {
        List<Book> books;

        if (sortByYear) {
            books = bookService.findBooksByPageAndSortByYear(page, booksPerPage);
        } else {
            books = bookService.findBooksByPage(page, booksPerPage);
        }

        model.addAttribute("books", books);
        return "books/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        Optional<Person> bookOwner = bookService.getBookOwner(id);
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", peopleService.findAll());
            model.addAttribute("person", new Person());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {

        bookService.assign(id, selectedPerson);

        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchBooks(
            @RequestParam(name = "titlePrefix", required = false) String titlePrefix,
            @RequestParam(name = "authorPrefix", required = false) String authorPrefix,
            Model model
    ) {
        List<Book> searchResults = new ArrayList<>();

        if (titlePrefix != null && !titlePrefix.isEmpty()) {
            searchResults.addAll(bookService.searchBooksByTitle(titlePrefix));
        }

        if (authorPrefix != null && !authorPrefix.isEmpty()) {
            searchResults.addAll(bookService.searchBooksByAuthor(authorPrefix));
        }

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("titlePrefix", titlePrefix);
        model.addAttribute("authorPrefix", authorPrefix);
        return "books/search";
    }

}
