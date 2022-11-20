package com.example.laboratory.work.project.dao;

import com.example.laboratory.work.project.model.Book;
import com.example.laboratory.work.project.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookDAO {

    private final JdbcTemplate template;

    public List<Book> index() {
        return template.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return template.query("select * from book where id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<Person> getBookOwner(int id) {
        return template.query("select person. * from book " +
                                "join person on book.person_id = person.id where book.id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

    public void save(Book book) {
        template.update("insert into  book(title, autor, year)values (?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public void delete(int id) {
        template.update("delete from book where id=?", id);
    }

    public void release(int id) {
        template.update("update  book set person_id=NULL where id=?", id);
    }

    public void assign(int id, Person person) {
        template.update("update  book set person_id=? where id=?", person.getId(), id);
    }

    public void update(int id, Book book) {
        template.update("update book set title=?, autor=?, year=? where id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(), id);
    }
}
