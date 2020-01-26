package com.javabeginnerstutorial.bootdemo.controller;

import com.javabeginnerstutorial.bootdemo.model.Person;
import com.javabeginnerstutorial.bootdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person addPagePerson(@RequestBody Person person) {
        Person createdPerson= personService.createPerson(person);
        return createdPerson;
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPerson() {
        return personService.getAllPersons();
    }
}