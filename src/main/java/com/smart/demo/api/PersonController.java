package com.smart.demo.api;

import com.smart.demo.model.Person;
import com.smart.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/persons")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public int create(@Valid @NotNull @RequestBody Person obj) {
        return service.create(obj);
    }

    @PutMapping(path = "{id}")
    public int update(@PathVariable("id") UUID id, @RequestBody Person obj) {
        return service.update(id, obj);
    }

    @DeleteMapping(path = "{id}")
    public int delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }

    @GetMapping
    public List<Person> get() {
        return service.get();
    }

    @GetMapping(path = "{id}")
    public Person get(@PathVariable("id") UUID id) {
        return service.get(id).orElse(null);
    }

    @GetMapping(path = "/search")
    public List<Person> get(@RequestParam("filter") String filter) {
        return service.get(filter);
    }
}
