package gameStore.store.services;

import gameStore.store.models.User;
import gameStore.store.repository.UserRepository;
import gameStore.store.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(String email, String password, String confirmPassword, String firstName, String lastName) {
        long count = userRepository.count();
        boolean isAdmin = false;

        if (count == 0) {
            isAdmin = true;
        }

        if (!password.equals(confirmPassword)) {
            throw new ConcurrencyFailureException("Password " + password + " is not the same as " + confirmPassword);
        }

        User user = new User(email, password, firstName, lastName, isAdmin);
        userRepository.save(user);
    }
}
