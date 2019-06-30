package ru.noosphere.services;

import ru.noosphere.entities.Video;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    List<String> separateToFrames(Video video, String pathToSaveFrames, int everyFrame) throws IOException;
    void downloadUsingStream(Video video, String file) throws IOException;
    Video save(Video video);
}
