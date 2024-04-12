package org.belotelov.hw4.repository;

import org.belotelov.hw4.config.DbQueries;
import org.springframework.stereotype.Repository;
import org.belotelov.hw4.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
public class PersonRepository {
    private final JdbcTemplate jdbc;

    private final DbQueries dbQueries;

    public PersonRepository(JdbcTemplate jdbc, DbQueries dbQueries) {
        this.jdbc = jdbc;
        this.dbQueries = dbQueries;
    }

    RowMapper<Person> personRowMapper = (r, i) -> {
        Person rowObject = new Person();
        rowObject.setId(r.getInt("id"));
        rowObject.setName(r.getString("name"));
        rowObject.setPhone(r.getString("phone"));
        rowObject.setEmail(r.getString("email"));
        rowObject.setNote(r.getString("note"));
        return rowObject;
    };

    public List<Person> findAll() {
        return jdbc.query(dbQueries.getFindAll(), personRowMapper);
    }

    public Person save(Person person) {
        jdbc.update(dbQueries.getSave(), person.getName(), person.getPhone(), person.getEmail(), person.getNote());
        return person;
    }

    public void deleteById(int id) {
        jdbc.update(dbQueries.getDelete(), id);
    }

    public Person getOne(int id) {
        return jdbc.query(dbQueries.getGet(), new Object[]{id}, personRowMapper).get(0);
    }

    public Person update(Person person) {
        jdbc.update(dbQueries.getUpdate(), person.getName(), person.getPhone(), person.getEmail(), person.getNote(), person.getId());
        return person;
    }
}
