package judgev2.service;

import judgev2.model.entity.UserEntity;
import judgev2.model.entity.enumeration.RoleName;
import judgev2.model.service.UserServiceModel;
import judgev2.model.view.UserProfileViewModel;

import java.util.List;

public interface UserService {
    void register(UserServiceModel userServiceModel);
    void login(UserServiceModel userServiceModel);
    UserServiceModel findByUsernameAndPassword(UserServiceModel userServiceModel);

    void logout();

    List<String> findAllUsernames();

    void changeRole(String username, RoleName valueOf);

    UserEntity findById(String id);

    UserProfileViewModel findProfileById(String id);

    Integer findUserCount();
}
