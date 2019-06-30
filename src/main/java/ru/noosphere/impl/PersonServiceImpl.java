package ru.noosphere.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noosphere.entities.Person;
import ru.noosphere.services.PersonService;
import ru.noosphere.services.repo.PersonRepo;

@Service("personService")
@Transactional
@Repository
public class PersonServiceImpl implements PersonService {

    private PersonRepo personRepo;

    @Override
    public Person save(Person person) {
        return personRepo.save(person);
    }

    @Override
    public Person fingByCelebrityId(String id) {
        return null;
    }

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }
}
