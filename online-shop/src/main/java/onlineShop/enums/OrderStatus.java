package onlineShop.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
