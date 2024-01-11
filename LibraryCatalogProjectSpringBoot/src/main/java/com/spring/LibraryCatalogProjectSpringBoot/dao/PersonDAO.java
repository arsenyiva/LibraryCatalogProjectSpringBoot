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
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public Optional<Person> show(String name) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name, year_of_birth) VALUES (?, ?)",
//                person.getName(), person.getYearOfBirth());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person Set name=?,year_of_birth=? WHERE id=?",
//                updatedPerson.getName(), updatedPerson.getYearOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public List<Book> getBooksByPersonId(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//}
