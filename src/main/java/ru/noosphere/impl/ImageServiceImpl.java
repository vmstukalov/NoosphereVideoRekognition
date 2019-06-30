package ru.noosphere.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noosphere.entities.Image;
import ru.noosphere.services.ImageService;
import ru.noosphere.services.repo.ImageRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("imageService")
@Transactional
@Repository
public class ImageServiceImpl implements ImageService {

    private ImageRepo imageRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Image> getNotScannedImages() {
        return (List<Image>) entityManager.createQuery("select i from Image i where i.scanned = 0").getResultList();
    }

    @Override
    public Image save(Image image) {
        return imageRepo.save(image);
    }

    @Autowired
    public void setImageRepo(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }
}
