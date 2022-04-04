package judgev2.service.impl;

import judgev2.model.entity.HomeworkEntity;
import judgev2.model.service.HomeworkServiceModel;
import judgev2.repository.HomeworkRepository;
import judgev2.security.CurrentUser;
import judgev2.service.ExerciseService;
import judgev2.service.HomeworkService;
import judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ModelMapper modelMapper, ExerciseService exerciseService, CurrentUser currentUser, UserService userService) {
        this.homeworkRepository = homeworkRepository;
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public HomeworkServiceModel findByScoring() {
        return homeworkRepository
                .findTop1ByOrderByCommentEntitySet()
                .map(h -> modelMapper.map(h, HomeworkServiceModel.class)).orElseThrow();
    }

    @Override
    public void addHomework(String exercise, String githubAddress) {
        HomeworkEntity homeworkEntity = new HomeworkEntity();

        homeworkEntity.setGithubAddress(githubAddress);
        homeworkEntity.setAddedOn(LocalDateTime.now());
        homeworkEntity.setExerciseEntity(exerciseService.findByName(exercise));
        homeworkEntity.setUser(userService.findById(currentUser.getId()));
        homeworkRepository.saveAndFlush(homeworkEntity);
    }

    @Override
    public HomeworkEntity findById(String homeworkId) {
        return homeworkRepository.findById(homeworkId).orElseThrow();
    }
}
