package com.javabeginnerstutorial.bootdemo.service;

import com.javabeginnerstutorial.bootdemo.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person createPerson(Person person);

    Optional<Person> getPerson(Long id);

    Person editPerson(Person person);

    void deletePerson(Person person);

    void deletePerson(Long id);

    List<Person> getAllPersons(int pageNumber, int pageSiz);

    List<Person> getAllPersons();
}
