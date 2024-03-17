package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.GroupEntity;
import com.vozhov.caesarapi.payload.request.user.GroupRequest;
import com.vozhov.caesarapi.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImp implements GroupService{
    private final GroupRepository groupRepository;
    @Override
    public List<GroupEntity> getGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void addGroup(GroupRequest request) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(request.getName());

        groupRepository.save(groupEntity);
    }
}