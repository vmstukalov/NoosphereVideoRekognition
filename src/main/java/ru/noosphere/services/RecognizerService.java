package ru.noosphere.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface RecognizerService {

    void recognize(String filePath) throws IOException;

}
