package entity;

import lombok.Getter;

@Getter
public class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;
}