package gameStore.store.services.interfaces;

import gameStore.store.models.entity.Role;

public interface RoleService {

    Role getRoleByName(Roles role);

    void updateRole(Role role);

    enum Roles {
        ADMIN, USER
    }
}
