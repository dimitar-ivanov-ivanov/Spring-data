package gameStore.store.repository;

import gameStore.store.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmailAndPassword(String email, String password);
}
