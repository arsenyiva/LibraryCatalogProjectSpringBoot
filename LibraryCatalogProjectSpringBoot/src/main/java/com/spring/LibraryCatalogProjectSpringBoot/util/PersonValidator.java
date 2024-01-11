package com.spring.LibraryCatalogProjectSpringBoot.util;


import com.spring.LibraryCatalogProjectSpringBoot.models.Person;
import com.spring.LibraryCatalogProjectSpringBoot.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        String name = person.getName();


        Optional<Person> existingPerson = peopleService.findOneByName(name);
        if (existingPerson.isPresent() && !Objects.equals(existingPerson.get().getId(), person.getId())) {
            errors.rejectValue("name", "", "This name already in use");
        }
    }


}

