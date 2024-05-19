package com.vozhov.caesarapi.repository;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByRole(RoleEntity role);
}
