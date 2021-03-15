package User.System.system.services;

import User.System.system.models.Picture;
import User.System.system.repositories.PictureRepository;
import User.System.system.services.interfaces.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void registerPicture(Picture picture) {
        this.pictureRepository.save(picture);
    }
}
