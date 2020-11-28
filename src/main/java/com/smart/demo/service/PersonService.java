package com.smart.demo.service;

import com.smart.demo.dao.PersonDao;
import com.smart.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao dao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao dao) {
        this.dao = dao;
    }

    public int create(Person obj) {
        return dao.insert(obj);
    }

    public int update(UUID id, Person obj) {
        return dao.update(id, obj);
    }

    public int delete() {
        return dao.delete();
    }

    public int delete(UUID id) {
        return dao.delete(id);
    }

    public List<Person> get() {
        return dao.find();
    }

    public List<Person> get(CharSequence filter) {
        return dao.find(filter);
    }

    public Optional<Person> get(UUID id) {
        return dao.find(id);
    }
}
