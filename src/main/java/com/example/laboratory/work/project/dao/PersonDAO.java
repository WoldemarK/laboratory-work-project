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
public class PersonDAO {

    private final JdbcTemplate template;

    public Optional<Person> getPersonByFullName(String fullName) {
        return template.query("select from person where full_name=?",
                        new Object[]{fullName},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

    public List<Person> index() {
        return template.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return template.query("select * from person where id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<Book> getBookByPersonId(int id) {
        return template.query("select from book where person_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void update(int id, Person person) {
        template.update("update person set full_name=?, year_of_birth=? where id=?",
                person.getFullName(),
                person.getYearOfBirth(), id);
    }

    public void save(Person person) {
        template.update("insert into person(full_name=?, year_of_birth=?) values (?,?)",
                person.getFullName(),
                person.getYearOfBirth());
    }

    public void delete(int id) {
        template.update("delete  from person  where id=?", id);
    }
}
