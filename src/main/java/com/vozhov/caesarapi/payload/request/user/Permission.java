package com.vozhov.caesarapi.payload.request.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    PROJECT_UPDATE("project:update"),
    PROJECT_DELETE("project:delete"),
    PROJECT_CREATE("project:create"),
    TASK_UPDATE("task:update"),
    TASK_DELETE("task:delete"),
    TASK_CREATE("task:create"),
    DESK_UPDATE("desk:update"),
    DESK_DELETE("desk:delete"),
    DESK_CREATE("desk:create");

    private final String permission;
}