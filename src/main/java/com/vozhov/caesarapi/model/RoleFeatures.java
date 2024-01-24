package com.vozhov.caesarapi.model;

public enum RoleFeatures {
    ADMINISTRATION ("Администрирование"),
    CONFIGURATION ("Настройка"),
    MODERATION ("Модерация"),
    TASK_CREATION ("Создание задач");

    private final String name;

    RoleFeatures (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}