package judgev2.service.impl;

import judgev2.model.entity.CommentEntity;
import judgev2.model.service.CommentServiceModel;
import judgev2.repository.CommentRepository;
import judgev2.security.CurrentUser;
import judgev2.service.CommentService;
import judgev2.service.HomeworkService;
import judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final HomeworkService homeworkService;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, HomeworkService homeworkService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.homeworkService = homeworkService;
    }


    @Override
    public void add(CommentServiceModel commentServiceModel, String homeworkId) {
        CommentEntity commentEntity = modelMapper
                .map(commentServiceModel
                        , CommentEntity.class);

        commentEntity.setAuthor(userService
                .findById(currentUser.getId()));

        commentEntity.setHomework(homeworkService
                .findById(homeworkId));

        commentRepository.saveAndFlush(commentEntity);
    }

    @Override
    public Double findAvgScore() {
        return commentRepository.findAverageScore();
    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer> scoreMap = initScoreMap();

        commentRepository
                .findAll()
                .forEach(c -> {
                    Integer score = c.getScore();

                    scoreMap.put(score, scoreMap.get(score) + 1);
                });

        return scoreMap;
    }

    private Map<Integer, Integer> initScoreMap() {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 2; i <= 6; i++) {
            map.put(i, 0);
        }

        return map;
    }
}
