package judgev2.service;

import judgev2.model.entity.ExerciseEntity;
import judgev2.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void add(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExerciseNames();

    boolean checkIfIsLate(String exercise);

    ExerciseEntity findByName(String exercise);
}
