package judgev2.service.impl;

import judgev2.model.entity.RoleEntity;
import judgev2.model.entity.UserEntity;
import judgev2.model.entity.enumeration.RoleName;
import judgev2.model.service.UserServiceModel;
import judgev2.model.view.UserProfileViewModel;
import judgev2.repository.UserRepository;
import judgev2.security.CurrentUser;
import judgev2.service.RoleService;
import judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userServiceModel.setRole(roleService
                .findByName(userRepository.count() == 0 ? RoleName.ADMIN : RoleName.USER));

        UserEntity userEntity = modelMapper
                .map(userServiceModel, UserEntity.class);

        userRepository.save(userEntity);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRole(userServiceModel.getRole().getName());

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(UserServiceModel userServiceModel) {
        UserEntity userEntity = userRepository
                .findByUsernameAndPassword(userServiceModel.getUsername()
                        , userServiceModel.getPassword());

        return userEntity == null ? null : modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public void logout() {
        currentUser.clear();
    }

    @Override
    public List<String> findAllUsernames() {
        return userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleName valueOf) {
        UserEntity user = userRepository.findByUsername(username);

        if (user.getRole().getName() != valueOf) {
            user.setRole(modelMapper.map(roleService.findByName(valueOf), RoleEntity.class));

            userRepository.save(user);
        }
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserProfileViewModel findProfileById(String id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        UserProfileViewModel userProfileViewModel =  modelMapper
                .map(userEntity, UserProfileViewModel.class);

        userProfileViewModel.setHomework(userEntity
                        .getHomework()
                        .stream()
                        .map(h -> h.getExerciseEntity().getName())
                        .collect(Collectors.toSet()));

        return userProfileViewModel;
    }

    @Override
    public Integer findUserCount() {
        return userRepository.findUserCount();
    }
}
