package ru.noosphere.services;

import ru.noosphere.entities.Video;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    List<String> separateToFrames(File video, String pathToSaveFrames, int sleep) throws IOException;
    void downloadUsingStream(String urlStr, String file) throws IOException;
    Video save(Video video);
}
