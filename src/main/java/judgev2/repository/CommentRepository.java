package judgev2.repository;

import judgev2.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query("SELECT AVG(c.score) FROM CommentEntity c")
    Double findAverageScore();
}
