package com.smart.demo.dao;

import com.smart.demo.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao{

    default int insert(Person obj) {
        return insert(UUID.randomUUID(), obj);
    }

    int insert(UUID id, Person obj);

    int update(UUID id, Person obj);

    int delete();

    int delete(UUID id);

    Optional<Person> find(UUID id);

    List<Person> find();

    List<Person> find(CharSequence filter);
}
