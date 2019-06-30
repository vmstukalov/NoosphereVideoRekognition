package ru.noosphere.services;

import ru.noosphere.entities.Image;

import java.util.List;

public interface ImageService {

    List<Image> getNotScannedImages();
    Image save(Image image);
}
