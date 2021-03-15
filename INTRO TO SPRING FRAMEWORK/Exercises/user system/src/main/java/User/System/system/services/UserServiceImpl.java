package User.System.system.services;

import User.System.system.models.User;
import User.System.system.repositories.UserRepository;
import User.System.system.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void registerUsers(Iterable<User> users) {
        this.userRepository.saveAll(users);
    }
}
