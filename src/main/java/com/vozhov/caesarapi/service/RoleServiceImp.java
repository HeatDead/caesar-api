package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.payload.request.user.Permission;
import com.vozhov.caesarapi.payload.request.user.RoleRequest;
import com.vozhov.caesarapi.repository.RoleRepository;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void createRole(String name) {
        RoleEntity re = new RoleEntity();
        re.setName(name);
        //re.setPermissions(roleFeatures);

        roleRepository.save(re);
    }

    @Override
    public void editRole(RoleRequest roleRequest) {
        Optional<RoleEntity> ro = roleRepository.findById(roleRequest.getId());
        if(ro.isPresent()) {
            RoleEntity re = ro.get();
            re.setName(roleRequest.getName());
            re.setPermissions(roleRequest.getPermissions());

            roleRepository.save(re);
        }
    }

    @Override
    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity getRole(Long id) {
        Optional<RoleEntity> ro = roleRepository.findById(id);
        return ro.orElse(null);
    }

    @Override
    public ResponseEntity<?> deleteRole(Long id) {
        Optional<RoleEntity> reo = roleRepository.findById(id);
        if (reo.isPresent()) {
            if (!userRepository.findByRole(reo.get()).isEmpty())
                return new ResponseEntity<>(HttpStatus.CONFLICT);

            roleRepository.delete(reo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
