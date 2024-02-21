package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.payload.request.RoleFeatures;
import com.vozhov.caesarapi.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public void createRole(String name, Set<RoleFeatures> roleFeatures) {
        RoleEntity re = new RoleEntity();
        re.setName(name);
        re.setRoleFeatures(roleFeatures);

        roleRepository.save(re);
    }
}
