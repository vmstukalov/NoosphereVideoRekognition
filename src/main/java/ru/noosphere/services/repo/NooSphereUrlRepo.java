package ru.noosphere.services.repo;

import org.springframework.data.repository.CrudRepository;
import ru.noosphere.entities.NooSphereURL;

public interface NooSphereUrlRepo extends CrudRepository<NooSphereURL, Long> {
}
