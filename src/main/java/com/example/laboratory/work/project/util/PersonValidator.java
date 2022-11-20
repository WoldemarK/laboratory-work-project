package com.example.laboratory.work.project.util;

import com.example.laboratory.work.project.dao.PersonDAO;
import com.example.laboratory.work.project.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.getPersonByFullName(person.getFullName()).isPresent())
        errors.rejectValue("fullName", "Человек с таким ФИО уже существует");
    }
}
