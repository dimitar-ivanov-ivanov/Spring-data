package productShop.shop.services.interfaces;

import productShop.shop.models.User;
import productShop.shop.models.dto.binding.UserDto;

public interface UserService {

    void saveAll(UserDto[] usersDto);

    User getRandomEntity();
}
