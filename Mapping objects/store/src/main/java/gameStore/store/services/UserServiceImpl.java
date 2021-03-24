package gameStore.store.services;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
import gameStore.store.models.dto.UserLoginBindingModel;
import gameStore.store.models.entity.Role;
import gameStore.store.models.entity.User;
import gameStore.store.models.dto.UserRegisterBindingModel;
import gameStore.store.repository.UserRepository;
import gameStore.store.services.interfaces.RoleService;
import gameStore.store.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private User loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        Role role = this.setUserRole(user);
        if (model.doPasswordsMatch()) {
            user = this.userRepository.saveAndFlush(user);
            this.roleService.updateRole(role);
        } else {
            throw new ConfirmedPasswordIsWrongException("Passwords do not match.");
        }

        return true;
    }

    @Override
    public boolean loginUser(UserLoginBindingModel model) {
        User user = this.userRepository.getUserByEmailAndPassword(model.getEmail(), model.getPassword());

        if (user != null) {
            loggedInUser = user;
            return true;
        }

        return false;
    }

    @Override
    public boolean logout() {
        if (loggedInUser != null) {
            loggedInUser = null;
            return true;
        }
        return false;
    }

    private Role setUserRole(User user) {
        Role role = null;
        if (this.userRepository.count() > 0) {
            role = this.roleService.getRoleByName(RoleService.Roles.USER);
            user.getRoles().add(role);
        } else {
            role = this.roleService.getRoleByName(RoleService.Roles.ADMIN);
            user.getRoles().add(role);
        }
        return role;
    }
}
