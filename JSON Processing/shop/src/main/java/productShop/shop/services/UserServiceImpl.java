package productShop.shop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productShop.shop.models.User;
import productShop.shop.models.dto.binding.UserDto;
import productShop.shop.repositories.UserRepository;
import productShop.shop.services.interfaces.UserService;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(UserDto[] usersDto) {
        User[] users = this.modelMapper.map(usersDto, User[].class);
        this.userRepository.saveAll(Arrays.asList(users));

        for (int i = 0; i < users.length; i++) {
            User user = this.userRepository.getRandomEntity();
            User friend = this.userRepository.getRandomEntity();

            if (user != null && friend != null && user.getId() != friend.getId()) {
                user.getFriends().add(friend);
                friend.getFriends().add(user);
                userRepository.save(user);
                userRepository.save(friend);
            }
        }


    }

    @Override
    public User getRandomEntity() {
        return userRepository.getRandomEntity();
    }
}
