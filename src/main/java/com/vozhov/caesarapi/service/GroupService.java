package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.GroupEntity;
import com.vozhov.caesarapi.payload.request.user.GroupRequest;

import java.util.List;

public interface GroupService {
    List<GroupEntity> getGroups();
    void addGroup(GroupRequest request);
}
