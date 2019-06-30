package ru.noosphere.services.repo;

import org.springframework.data.repository.CrudRepository;
import ru.noosphere.entities.Video;

public interface VideoRepo extends CrudRepository<Video, Long> {
}
