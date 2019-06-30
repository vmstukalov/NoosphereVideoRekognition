package ru.noosphere.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noosphere.entities.Video;
import ru.noosphere.services.VideoService;
import ru.noosphere.services.repo.VideoRepo;

@Service("videoService")
@Transactional
@Repository
public class VideoServiceImpl implements VideoService {

    private VideoRepo repo;

    public Video save(Video video) {
        return repo.save(video);
    }

    @Autowired
    public void setRepo(VideoRepo repo) {
        this.repo = repo;
    }
}
