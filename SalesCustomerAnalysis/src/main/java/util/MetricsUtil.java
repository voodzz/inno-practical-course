package util;

import entity.Customer;
import entity.Order;
import entity.OrderItem;
import entity.OrderStatus;
import lombok.experimental.UtilityClass;

import java.util.List;

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
     * Returns the list of unique cities where orders came from
     * @param orders the list of orders
     * @return the list of unique cities where orders came from
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
}
