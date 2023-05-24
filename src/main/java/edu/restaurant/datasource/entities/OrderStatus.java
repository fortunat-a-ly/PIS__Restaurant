package edu.restaurant.datasource.entities;

public enum OrderStatus {
    PREPARING(1),
    READY(2);

    private final int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus r : OrderStatus.values()) {
            if (code == r.getCode()) {
                return r;
            }
        }
        return null;
    }
}
