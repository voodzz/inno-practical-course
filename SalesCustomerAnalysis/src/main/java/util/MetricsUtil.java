package util;

import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.OrderStatus;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Utility class to count the following metrics:
 * <ul>
 * <li>List of unique cities where orders came from</li>
 * <li>Total income for all completed orders</li>
 * <li>The most popular product by sales</li>
 * <li>Average check for successfully delivered orders</li>
 * <li>Customers who have more than 5 orders</li>
 * </ul>
 */
@UtilityClass
public class MetricsUtil {

    /**
     * Returns a list of unique cities where orders came from
     *
     * @param orders the list of orders
     * @return a list of unique cities where orders came from
     */
    public List<String> getListOfUniqueCities(List<Order> orders) {
        return orders.stream()
                .map(Order::getCustomer)
                .map(Customer::getCity)
                .distinct()
                .toList();
    }

    /**
     * Counts total income for all completed orders
     *
     * @param orders the list of orders
     * @return total income for all completed orders
     */
    public double getTotalIncomeForAllCompletedOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(order -> order.getPrice() * order.getQuantity())
                .sum();
    }

    /**
     * Finds the most popular product by sales in the given list of orders
     *
     * @param orders the list of orders
     * @return the most popular product by sales
     */
    public String getMostPopularProductBySales(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    /**
     * Counts the average check for successfully delivered orders
     * @param orders the list of orders
     * @return the average check for successfully delivered orders
     */
    public double countAverageCheckForDeliveredOrders(List<Order> orders) {
        ToDoubleFunction<List<OrderItem>> mapToChecks;
        mapToChecks = list -> list.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .map(Order::getItems)
                .mapToDouble(mapToChecks)
                .average()
                .orElse(0);
    }

    /**
     * Returns a list of customers with more than 5 orders
     * @param orders the list of orders
     * @return a list of customers with more than 5 orders
     */
    public List<Customer> getCustomersWithMoreThanFiveOrders(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .toList();
    }
}
