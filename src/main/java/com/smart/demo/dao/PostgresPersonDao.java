package com.smart.demo.dao;

import com.smart.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("postgresDao")
public class PostgresPersonDao implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresPersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(UUID id, Person obj) {
        return 0;
    }

    @Override
    public int update(UUID id, Person obj) {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }

    @Override
    public int delete(UUID id) {
        return 0;
    }

    @Override
    public List<Person> find() {
        return jdbcTemplate.query("select * from person;",
                (resultSet, o) -> new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name")
                ));
    }

    @Override
    public List<Person> find(CharSequence filter) {
        return jdbcTemplate.query("select * from person where name like \'%?%\';",
                new Object[]{filter},
                (resultSet, o) -> new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name")
                ));
    }

    @Override
    public Optional<Person> find(UUID id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from person where id=?;",
                        new Object[]{id},
                        (resultSet, o) -> new Person(
                                UUID.fromString(resultSet.getString("id")),
                                resultSet.getString("name")
                        ))
        );
    }
}
