package judgev2.service.impl;

import judgev2.model.entity.ExerciseEntity;
import judgev2.model.service.ExerciseServiceModel;
import judgev2.repository.ExerciseRepository;
import judgev2.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ExerciseServiceModel exerciseServiceModel) {
        exerciseRepository.saveAndFlush(modelMapper.map(exerciseServiceModel, ExerciseEntity.class));
    }

    @Override
    public List<String> findAllExerciseNames() {
        return
                exerciseRepository
                        .findAllExerciseNames();
    }

    @Override
    public boolean checkIfIsLate(String exercise) {
        ExerciseEntity exerciseEntity = findByName(exercise);


        return exerciseEntity != null && exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public ExerciseEntity findByName(String exercise) {
        return exerciseRepository.findByName(exercise).orElse(null);
    }
}
