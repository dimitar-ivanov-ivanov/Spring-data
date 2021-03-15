package User.System.system.services;

import User.System.system.models.Album;
import User.System.system.repositories.AlbumRepository;
import User.System.system.services.interfaces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void registerAlbum(Album album) {
        this.albumRepository.save(album);
    }
}
