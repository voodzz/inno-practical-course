package util;

import entity.*;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class TestDataImporter {
    public List<Order> importData() {
        Customer customer1 = Customer.builder()
                .customerId("1")
                .name("Ruslan")
                .email("ruslan@example.com")
                .registeredAt(LocalDateTime.now())
                .age(20)
                .city("Grodno")
                .build();

        Customer customer2 = Customer.builder()
                .customerId("2")
                .name("Stepan")
                .email("stepan@example.com")
                .registeredAt(LocalDateTime.now())
                .age(20)
                .city("Grodno")
                .build();

        Customer customer3 = Customer.builder()
                .customerId("3")
                .name("Pavel")
                .email("pavel@example.com")
                .registeredAt(LocalDateTime.now())
                .age(45)
                .city("Brest")
                .build();

        Customer customer4 = Customer.builder()
                .customerId("4")
                .name("Elena")
                .email("elena@example.com")
                .registeredAt(LocalDateTime.now())
                .age(38)
                .city("Vitebsk")
                .build();

        Customer customer5 = Customer.builder()
                .customerId("5")
                .name("Sergey")
                .email("sergey@example.com")
                .registeredAt(LocalDateTime.now())
                .age(52)
                .city("Gomel")
                .build();

        Customer customer6 = Customer.builder()
                .customerId("6")
                .name("Vika")
                .email("vika@example.com")
                .registeredAt(LocalDateTime.now())
                .age(21)
                .city("Mogilev")
                .build();

        OrderItem item1 = new OrderItem("Laptop", 1, 1200.50, Category.ELECTRONICS);
        OrderItem item2 = new OrderItem("Shampoo", 2, 9.50, Category.BEAUTY);
        OrderItem item3 = new OrderItem("The Hitchhiker's Guide to the Galaxy", 1, 15.99, Category.BOOKS);
        OrderItem item4 = new OrderItem("Blender", 1, 75.00, Category.HOME);
        OrderItem item5 = new OrderItem("T-Shirt", 5, 25.50, Category.CLOTHING);
        OrderItem item6 = new OrderItem("Action Figure", 4, 12.00, Category.TOYS);
        OrderItem item7 = new OrderItem("Smartphone", 1, 899.00, Category.ELECTRONICS);
        OrderItem item8 = new OrderItem("Face Cream", 3, 28.50, Category.BEAUTY);
        OrderItem item9 = new OrderItem("Java Programming Book", 1, 45.00, Category.BOOKS);
        OrderItem item10 = new OrderItem("Coffee Maker", 1, 55.75, Category.HOME);
        OrderItem item11 = new OrderItem("T-Shirt", 1, 25.50, Category.CLOTHING);
        OrderItem item12 = new OrderItem("Puzzle Game", 1, 22.50, Category.TOYS);
        OrderItem item13 = new OrderItem("Wireless Headphones", 1, 150.00, Category.ELECTRONICS);
        OrderItem item14 = new OrderItem("Scented Candle", 1, 15.00, Category.HOME);
        OrderItem item15 = new OrderItem("Science Fiction Novel", 1, 18.25, Category.BOOKS);
        OrderItem item16 = new OrderItem("Vacuum Cleaner", 1, 350.00, Category.HOME);
        OrderItem item17 = new OrderItem("Sneakers", 1, 110.00, Category.CLOTHING);
        OrderItem item18 = new OrderItem("Laptop", 2, 1200.50, Category.ELECTRONICS);

        List<OrderItem> orderItemList1 = List.of(item1, item2, item3);
        List<OrderItem> orderItemList2 = List.of(item4);
        List<OrderItem> orderItemList3 = List.of(item5, item6, item7, item8, item9, item10);
        List<OrderItem> orderItemList4 = List.of(item11, item12, item13, item14, item15);
        List<OrderItem> orderItemList5 = List.of(item16);
        List<OrderItem> orderItemList6 = List.of(item17, item18);


        Order order1 = Order.builder()
                .orderId("1")
                .orderDate(LocalDateTime.now())
                .customer(customer1)
                .items(orderItemList1)
                .status(OrderStatus.DELIVERED)
                .build();

        Order order2 = Order.builder()
                .orderId("2")
                .orderDate(LocalDateTime.now())
                .customer(customer2)
                .items(orderItemList2)
                .status(OrderStatus.SHIPPED)
                .build();

        Order order3 = Order.builder()
                .orderId("3")
                .orderDate(LocalDateTime.now())
                .customer(customer3)
                .items(orderItemList3)
                .status(OrderStatus.DELIVERED)
                .build();

        Order order4 = Order.builder()
                .orderId("4")
                .orderDate(LocalDateTime.now())
                .customer(customer4)
                .items(orderItemList4)
                .status(OrderStatus.PROCESSING)
                .build();

        Order order5 = Order.builder()
                .orderId("5")
                .orderDate(LocalDateTime.now())
                .customer(customer5)
                .items(orderItemList5)
                .status(OrderStatus.CANCELLED)
                .build();

        Order order6 = Order.builder()
                .orderId("6")
                .orderDate(LocalDateTime.now())
                .customer(customer6)
                .items(orderItemList6)
                .status(OrderStatus.NEW)
                .build();

        return List.of(order1, order2, order3, order4, order5, order6);
    }

    public void addOrdersWithOneCustomer(List<Order> orders) {
        Customer customer7 = Customer.builder()
                .customerId("7")
                .name("Sania")
                .email("alex@example.com")
                .registeredAt(LocalDateTime.now())
                .age(21)
                .city("Minsk")
                .build();
        OrderItem item5 = new OrderItem("T-Shirt", 5, 25.50, Category.CLOTHING);


        Order order7 = Order.builder()
                .orderId("7")
                .orderDate(LocalDateTime.now())
                .customer(customer7)
                .items(List.of(item5))
                .status(OrderStatus.NEW)
                .build();
        Order order8 = Order.builder()
                .orderId("8")
                .orderDate(LocalDateTime.now())
                .customer(customer7)
                .items(List.of(item5))
                .status(OrderStatus.CANCELLED)
                .build();
        Order order9 = Order.builder()
                .orderId("9")
                .orderDate(LocalDateTime.now())
                .customer(customer7)
                .items(List.of(item5))
                .status(OrderStatus.NEW)
                .build();
        Order order10 = Order.builder()
                .orderId("10")
                .orderDate(LocalDateTime.now())
                .customer(customer7)
                .items(List.of(item5))
                .status(OrderStatus.PROCESSING)
                .build();
        Order order11 = Order.builder()
                .orderId("11")
                .orderDate(LocalDateTime.now())
                .customer(customer7)
                .items(List.of(item5))
                .status(OrderStatus.SHIPPED)
                .build();
        Order order12 = Order.builder()
                .orderId("12")
                .orderDate(LocalDateTime.now())
                .customer(customer7)
                .items(List.of(item5))
                .status(OrderStatus.DELIVERED)
                .build();

        orders.add(order7);
        orders.add(order8);
        orders.add(order9);
        orders.add(order10);
        orders.add(order11);
        orders.add(order12);
    }
}
