package gameStore.store.repository;

import java.util.List;

import gameStore.store.models.entity.Game;
import gameStore.store.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmailAndPassword(String email, String password);

    @Query("SELECT u.games FROM User AS u WHERE u.id = :id ")
    List<Game> getGamesOfUser(@Param("id") long id);
}
