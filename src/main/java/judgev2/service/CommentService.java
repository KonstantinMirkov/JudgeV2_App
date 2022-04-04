package judgev2.service;

import judgev2.model.service.CommentServiceModel;

import java.util.Map;

public interface CommentService {
    void add(CommentServiceModel commentServiceModel, String homeworkId);

    Double findAvgScore();

    Map<Integer, Integer> findScoreMap();
}
