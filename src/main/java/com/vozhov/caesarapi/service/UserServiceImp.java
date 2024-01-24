package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(String name, String surname, String patronymic, String login, String password) {
        UserEntity ue = new UserEntity();
        ue.setLogin(login);
        ue.setName(name);
        ue.setSurname(surname);
        ue.setPatronymic(patronymic);
        ue.setPassword(password);

        userRepository.save(ue);
    }

    @Override
    public void addRole(Long userId, Set<Long> roles) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isPresent()) {
            UserEntity ue = userEntityOptional.get();
            ue.setRoles(roles);
            userRepository.save(ue);
        }
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}