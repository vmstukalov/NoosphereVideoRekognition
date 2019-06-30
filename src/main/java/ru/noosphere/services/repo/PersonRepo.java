package ru.noosphere.services.repo;

import org.springframework.data.repository.CrudRepository;
import ru.noosphere.entities.Person;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
