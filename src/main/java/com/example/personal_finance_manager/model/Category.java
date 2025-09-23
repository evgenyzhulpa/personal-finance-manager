package com.example.personal_finance_manager.model;

public enum Category {
    SALARY("Зарплата"),
    FOOD("Еда"),
    TRANSPORT("Транспорт"),
    ENTERTAINMENT("Развлечения"),
    UTILITIES("Коммунальные услуги"),
    HEALTH("Здоровье"),
    OTHER("Прочее");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
