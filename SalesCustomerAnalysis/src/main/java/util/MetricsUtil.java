package util;

import entity.Customer;
import entity.Order;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Utility class to count the following metrics:
 * <ol>
 * <li>List of unique cities where orders came from</li>
 * <li>Total income for all completed orders</li>
 * <li>The most popular product by sales</li>
 * <li>Average check for successfully delivered orders</li>
 * <li>Customers who have more than 5 orders</li>
 * </ol>
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
}
