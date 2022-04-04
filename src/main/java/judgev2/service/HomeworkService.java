package judgev2.service;

import judgev2.model.entity.HomeworkEntity;
import judgev2.model.service.HomeworkServiceModel;

public interface HomeworkService {
    HomeworkServiceModel findByScoring();

    void addHomework(String exercise, String githubAddress);

    HomeworkEntity findById(String homeworkId);
}
