package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.GroupEntity;
import com.vozhov.caesarapi.payload.request.user.GroupRequest;
import com.vozhov.caesarapi.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<GroupEntity> getGroups() {
        return groupService.getGroups();
    }

    @PostMapping
    public void addGroup(@RequestBody GroupRequest groupRequest) {
        groupService.addGroup(groupRequest);
    }
}
