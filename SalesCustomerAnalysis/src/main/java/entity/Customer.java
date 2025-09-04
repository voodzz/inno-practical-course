package entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    private int age;
    private String city;
}