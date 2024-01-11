package com.spring.LibraryCatalogProjectSpringBoot.dao;//package com.spring.app.dao;
//
//import com.spring.app.models.Book;
//import com.spring.app.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(title,author,year) VALUES (?,?,?)",
//                book.getTitle(),book.getAuthor(),book.getYear());
//    }
//
//    public void update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE Book Set title=?,author=?,year=? WHERE id=?",
//                updatedBook.getTitle(), updatedBook.getAuthor(),updatedBook.getYear(),id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//    public Optional<Person> getBookOwner(int id) {
//        return jdbcTemplate.query(
//                "SELECT  Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
//                        "WHERE Book.id=?",
//                new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)
//        ).stream().findAny();
//    }
//    public void release (int id){
//        jdbcTemplate.update("UPDATE Book Set person_id=NULL WHERE id=?", id);
//    }
//    public void assign(int id, Person selectedPerson){
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",selectedPerson.getId(),id);
//    }
//}
////
//public void updateBookPersonId(int bookId, Person selectedPerson) {
//        Book book = bookRepository.findById(bookId)
//        .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
//
//        book.setPerson(selectedPerson);
//        bookRepository.save(book);
//        }
