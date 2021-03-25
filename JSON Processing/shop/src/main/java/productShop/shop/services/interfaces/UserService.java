package productShop.shop.services.interfaces;

import productShop.shop.models.dto.UserDto;

public interface UserService {

    void saveAll(UserDto[] usersDto);
}
