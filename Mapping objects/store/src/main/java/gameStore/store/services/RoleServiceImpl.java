package gameStore.store.services;

import gameStore.store.models.entity.Role;
import gameStore.store.repository.RoleRepository;
import gameStore.store.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(Roles role) {
        return this.roleRepository.getRoleByName(role.name());
    }

    @Override
    public void updateRole(Role role) {
        this.roleRepository.save(role);
    }
}
