package User.System.system.services.interfaces;

import java.util.List;
import User.System.system.models.User;
import org.springframework.stereotype.Service;

public interface UserService {

    void registerUser(User user);

    void registerUsers(Iterable<User> users);
}
