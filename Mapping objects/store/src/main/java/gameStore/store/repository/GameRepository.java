package gameStore.store.repository;

import java.util.List;

import gameStore.store.models.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /* Updating a game the hard way */
    @Modifying
    @Transactional
    @Query("UPDATE Game AS g " +
            "SET g.title = :title, " +
            " g.trailer = :trailer, " +
            " g.thumbnail = :thumbnail, " +
            " g.price = :price, " +
            " g.size = :size, " +
            " g.description = :description " +
            "WHERE g.id = :id ")
    void updateGame(@Param("id") long id,
                    @Param("title") String title,
                    @Param("trailer") String trailer,
                    @Param("thumbnail") String thumbnail,
                    @Param("price") BigDecimal price,
                    @Param("size") Double size,
                    @Param("description") String description);

    @Modifying
    @Transactional
    @Query("DELETE Game AS g WHERE g.id = :id")
    void deleteGame(@Param("id") long id);

    @Query("SELECT g.title,g.price FROM Game AS g")
    List<Object[]> getGamesTitlesAndPrices();

    Game getGameByTitle(String title);
}
