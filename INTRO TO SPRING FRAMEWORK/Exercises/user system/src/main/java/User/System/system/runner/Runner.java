package User.System.system.runner;

import User.System.system.models.*;
import User.System.system.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.time.LocalDateTime;

@Configuration
public class Runner {

    @Autowired
    private UserService userService;

    @Autowired
    private TownService townService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AlbumService albumService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            User dimitar = new User(
                    "d1mn",
                    "Dimitar$1234",
                    "dimitar.i.ivanov@abv.bg",
                    new byte[]{1, 2, 3, 4},
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    22,
                    false,
                    "Dimitar",
                    "Ivanov"
            );

            User ivan = new User(
                    "iv!n",
                    "Ivan##1231a#@",
                    "ivan.i.ivanov@abv.bg",
                    new byte[]{1, 2, 3, 4},
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    25,
                    false,
                    "Ivan",
                    "Ivanov"
            );

            Country bulgaria = new Country("Bulgaria");

            Town haskovo = new Town(
                    "Haskovo",
                    bulgaria
            );

            Town sofia = new Town(
                    "Sofia",
                    bulgaria
            );

            Picture picture = new Picture(
                    "a",
                    "b",
                    "c"
            );

            Album album = new Album(
                    "a",
                    "b",
                    true
            );

            album.getPictures().add(picture);

            dimitar.setBornTown(haskovo);
            dimitar.setLivingTown(sofia);
            ivan.setLivingTown(haskovo);
            ivan.setBornTown(sofia);

            dimitar.getFriends().add(ivan);
            dimitar.getAlbums().add(album);

            countryService.registerCountry(bulgaria);
            townService.registerTowns(List.of(haskovo, sofia));
            pictureService.registerPicture(picture);
            albumService.registerAlbum(album);
            userService.registerUsers(List.of(dimitar, ivan));
        };
    }
}
