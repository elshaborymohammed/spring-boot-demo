package com.smart.demo.dao;

import com.smart.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakePersonDao implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    static {
        DB.add(new Person(UUID.fromString("6e1d890c-df3f-4a06-b1bc-fd3bf2166f72"), "Mohammed Elshabory"));
        DB.add(new Person(UUID.fromString("3d254dbd-16fc-4b5e-a852-05237ba4dec5"), "Nelson Mandela"));
        DB.add(new Person(UUID.fromString("ceef9dba-1076-4440-b186-57a53eeb7201"), "Elon Musk"));
        DB.add(new Person(UUID.fromString("9857d2ca-632d-40c4-ab6f-1922111e3c60"), "Jeff Bezos"));
        DB.add(new Person(UUID.fromString("e2ccc08b-7923-4f07-8587-ed16326f81c8"), "Bill Gates"));
    }

    @Override
    public int insert(UUID id, Person obj) {
        DB.add(new Person(id, obj.getName()));
        return 1;
    }

    @Override
    public int update(UUID id, Person obj) {
        return find(id).map(it -> {
            int index = DB.indexOf(it);
            if (index >= 0) {
                DB.set(index, new Person(id, obj.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public int delete() {
        DB.clear();
        return 1;
    }

    @Override
    public int delete(UUID id) {
        Optional<Person> personOptional = find(id);
        if (personOptional.isEmpty()) {
            return 0;
        } else {
            DB.remove(personOptional.get());
            return 1;
        }
    }

    @Override
    public List<Person> find() {
        return DB;
    }

    @Override
    public List<Person> find(CharSequence filter) {
        return DB.stream()
                .filter(person -> person.getName().contains(filter))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> find(UUID id) {
        return DB.stream().filter(it -> it.getId().equals(id)).findFirst();
    }
}
