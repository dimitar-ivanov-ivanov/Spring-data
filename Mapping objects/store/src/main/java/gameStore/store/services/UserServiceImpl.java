package gameStore.store.services;

import gameStore.store.exceptions.ConfirmedPasswordIsWrongException;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        User user = this.modelMapper.map(modelMapper, User.class);
        Role role = this.setUserRole(user);
        if (userRegisterBindingModel.doPasswordsMatch()) {
            user = this.userRepository.saveAndFlush(user);
            role.getUsers().add(user);
            this.roleService.updateRole(role);
        } else {
            throw new ConfirmedPasswordIsWrongException("Passwords do not match.");
        }

        return true;
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
