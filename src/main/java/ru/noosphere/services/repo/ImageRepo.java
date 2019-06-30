package ru.noosphere.services.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.noosphere.entities.Image;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
