package judgev2.repository;

import judgev2.model.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, String> {
    Optional<HomeworkEntity> findTop1ByOrderByCommentEntitySet();
}
