package gameStore.store.repository;

import gameStore.store.models.entity.Role;
import gameStore.store.services.interfaces.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(String name);
}
