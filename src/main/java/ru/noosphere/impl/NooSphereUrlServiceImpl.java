package ru.noosphere.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noosphere.entities.NooSphereURL;
import ru.noosphere.services.NooSphereUrlService;
import ru.noosphere.services.repo.NooSphereUrlRepo;

@Service("nooSphereUrlService")
@Transactional
@Repository
public class NooSphereUrlServiceImpl implements NooSphereUrlService {

    private NooSphereUrlRepo repo;

    public NooSphereURL save(NooSphereURL url) {
        return repo.save(url);
    }

    @Autowired
    public void setRepo(NooSphereUrlRepo repo) {
        this.repo = repo;
    }
}
