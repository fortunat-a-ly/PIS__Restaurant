package edu.restaurant.datasource.entities;

public enum UserRole {
    CUSTOMER(1),
    ADMINISTRATOR(2);

    private final int code;

    private UserRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserRole fromCode(int code) {
        for (UserRole r : UserRole.values()) {
            if (code == r.getCode()) {
                return r;
            }
        }
        return null;
    }
}
