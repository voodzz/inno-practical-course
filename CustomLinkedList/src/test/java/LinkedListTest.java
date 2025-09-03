import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    public void initList() {
        list = new LinkedList<>();
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
        list.addFirst(10);
        assertEquals(10, list.getFirst());
    }

    @Test
    void addLastTest() {
        list.addLast(10);
        assertEquals(10, list.getLast());
    }

    @Test
    void addTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(100, 10));

        list.add(3, 10);
        assertEquals(10, list.get(3));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(4));

        list.add(0, 11);
        assertEquals(11, list.getFirst());
        assertEquals(0, list.get(1));

        list.add(12, 12);
        assertEquals(12, list.getLast());
        assertEquals(9, list.get(list.size() - 2));
    }

    @Test
    void getFirstTest() {
        assertEquals(0, list.getFirst());

        list = new LinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
    }

    @Test
    void getLastTest() {
        assertEquals(9, list.getLast());

        list = new LinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }

    @Test
    void getTest() {
        assertEquals(0, list.get(0));
        assertEquals(9, list.get(9));
        assertEquals(3, list.get(3));
    }

    @Test
    void removeFirstTest() {
        assertEquals(0, list.removeFirst());
        assertEquals(1, list.getFirst());
        assertEquals(9, list.size());

        list = new LinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }

    @Test
    void removeLastTest() {
        assertEquals(9, list.removeLast());
        assertEquals(8, list.getLast());
        assertEquals(9, list.size());

        list = new LinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    void removeTest() {
        // first element
        assertEquals(0, list.remove(0));
        assertEquals(1, list.getFirst());
        assertEquals(9, list.size());

        // last element
        assertEquals(9, list.remove(list.size() - 1));
        assertEquals(8, list.getLast());
        assertEquals(8, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1000));

        // second element
        assertEquals(2, list.remove(1));
        assertEquals(1, list.getFirst());
        assertEquals(3, list.get(1));

        // element somewhere in the middle of the list
        assertEquals(5, list.remove(3));
        assertEquals(4, list.get(2));
        assertEquals(6, list.get(3));

        // pre-last element
        assertEquals(7, list.remove(4));
        assertEquals(6, list.get(3));
        assertEquals(8, list.get(4));

    }
}
