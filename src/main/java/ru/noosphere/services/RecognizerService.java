package ru.noosphere.services;

import ru.noosphere.entities.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface RecognizerService {

    List<Person> recognize(String filePath) throws IOException;

}
