package com.vozhov.caesarapi.config;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.user.Permission;
import com.vozhov.caesarapi.repository.RoleRepository;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        List<RoleEntity> roles = roleRepository.findAll();
        if(roles.isEmpty()) {
            RoleEntity re = new RoleEntity();
            re.setName("Администратор");
            Set<Permission> permissions = new HashSet<>(Arrays.asList(Permission.values()));
            re.setPermissions(permissions);
            roleRepository.save(re);
        }
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            UserEntity user = UserEntity.builder()
                    .username("admin")
                    .name("Имя")
                    .surname("Фамилия")
                    .patronymic("Отчество")
                    .role(roleRepository.findByName("Администратор"))
                    .password(passwordEncoder.encode("admin"))
                    .build();
            userRepository.save(user);
        }
    }
}
