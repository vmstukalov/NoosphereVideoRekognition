package ru.noosphere.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    List<String> separateToFrames(File video, String pathToSaveFrames, int sleep) throws IOException;
    void downloadUsingStream(String urlStr, String file) throws IOException;
}
