package judgev2.repository;

import judgev2.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, String> {

    @Query("SELECT e.name FROM ExerciseEntity e ORDER BY e.name")
    List<String> findAllExerciseNames();

    Optional<ExerciseEntity> findByName(String exercise);
}
