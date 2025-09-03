import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    public void initList() {
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
    }


    @Test
    void sizeTest() {
        assertEquals(10, list.size(), "Size is not 10 after addition of 10 elements");
    }

    @Test
    void addFirstTest() {

    }

    @Test
    void addLastTest() {}

    @Test
    void addTest() {}

    @Test
    void getFirstTest() {}

    @Test
    void getLastTest() {}

    @Test
    void getTest() {}

    @Test
    void removeFirstTest() {}

    @Test
    void removeLastTest() {}

    @Test
    void removeTest() {}
}
