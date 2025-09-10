import entity.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import util.MetricsUtil;
import util.TestDataImporter;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MetricsTest {
    List<Order> orders;

    @BeforeAll
    public void initOrders() {
        orders = TestDataImporter.importData();
    }

    @Test
    public void uniqueCitiesTest() {
        List<String> cities = MetricsUtil.getListOfUniqueCities(orders);
        assertEquals(5, cities.size());
        assertTrue(cities.contains("Grodno"));
        assertTrue(cities.contains("Brest"));
        assertTrue(cities.contains("Vitebsk"));
        assertTrue(cities.contains("Mogilev"));
        assertTrue(cities.contains("Gomel"));
    }

    @Test
    public void totalIncomeForCompletedOrdersTest() {
        double total = MetricsUtil.getTotalIncomeForAllCompletedOrders(orders);
        assertEquals(2496.24, total);

        List<Order> emptyList = new ArrayList<>();
        assertEquals(0, MetricsUtil.getTotalIncomeForAllCompletedOrders(emptyList));
    }

    @Test
    public void mostPopularProductTest() {
        String product = MetricsUtil.getMostPopularProductBySales(orders);
        assertEquals("T-Shirt", product);

        List<Order> emptyList = new ArrayList<>();
        assertThrows(NoSuchElementException.class, () -> MetricsUtil.getMostPopularProductBySales(emptyList));
    }

    @Test
    public void getAverageCheckForDeliveredOrdersTest() {
        double check = MetricsUtil.countAverageCheckForDeliveredOrders(orders);
        assertEquals(2496.24 / 2, check);

        List<Order> noDeliveredList = new ArrayList<>(orders);
        noDeliveredList.removeFirst();
        noDeliveredList.remove(1);

        check = MetricsUtil.countAverageCheckForDeliveredOrders(noDeliveredList);
        assertEquals(0, check);
    }

    @Test
    public void customerWithMoreThanFiveOrdersTest() {
        var customers = MetricsUtil.getCustomersWithMoreThanFiveOrders(orders);
        assertTrue(customers.isEmpty());

        List<Order> listWithCustomer = new ArrayList<>(orders);
        TestDataImporter.addOrdersWithOneCustomer(listWithCustomer);

        var oneCustomerList = MetricsUtil.getCustomersWithMoreThanFiveOrders(listWithCustomer);
        assertEquals(1, oneCustomerList.size());
        assertEquals("7", oneCustomerList.getFirst().getCustomerId());
    }
}
