package judgev2.repository;

import judgev2.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsernameAndPassword(String username, String password);

    @Query("SELECT u.username FROM UserEntity u ORDER BY u.username")
    List<String> findAllUsernames();

    UserEntity findByUsername(String username);

    @Query("SELECT COUNT(u) FROM UserEntity u")
    Integer findUserCount();
}
