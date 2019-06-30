package ru.noosphere.services;

import ru.noosphere.entities.Person;

public interface PersonService {

    Person save(Person person);
    Person fingByCelebrityId(String id);
}
