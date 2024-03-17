package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.GroupEntity;
import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.repository.GroupRepository;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public void createUser(String name, String surname, String patronymic, String login, String password) {
        UserEntity ue = UserEntity.builder()
                .build();
        ue.setUsername(login);
        ue.setName(name);
        ue.setSurname(surname);
        ue.setPatronymic(patronymic);
        ue.setPassword(password);

        userRepository.save(ue);
    }

    @Override
    public void addRole(String userId, RoleEntity role) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isPresent()) {
            UserEntity ue = userEntityOptional.get();
            ue.setRole(role);
            userRepository.save(ue);
        }
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addGroup(String userId, Long groupId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        Optional<GroupEntity> groupEntityOptional = groupRepository.findById(groupId);
        if(userEntityOptional.isPresent() && groupEntityOptional.isPresent()) {
            UserEntity ue = userEntityOptional.get();
            ue.getGroups().add(groupEntityOptional.get());
            userRepository.save(ue);
        }
    }
}