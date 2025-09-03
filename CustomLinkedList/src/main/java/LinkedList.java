import java.util.NoSuchElementException;

public class LinkedList<T> {

    /**
     * Size of the list
     */
    private int size = 0;

    /**
     * Pointer to the first node
     */
    private Node<T> first;

    /**
     * Pointer to the last node
     */
    private Node<T> last;

    /**
     * Constructs an empty list
     */
    public LinkedList() {}

    /**
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Inserts the element at the start of the list
     * @param el the element to insert
     */
    public void addFirst(T el) {
        size++;
        Node<T> oldFirst = first;
        first = new Node<>(el, oldFirst, null);
        if (oldFirst == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
    }

    /**
     * Inserts the element at the end of the list
     * @param el the element to insert
     */
    public void addLast(T el) {
        size++;
        Node<T> oldLast = last;
        last = new Node<>(el, null, oldLast);
        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    /**
     * Inserts the element at the given index
     * @param index index at which the element is to be inserted
     * @param el element to be inserted
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, T el) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }

        if (index == size) {
            addLast(el);
            return;
        }
        if (index == 0) {
            addFirst(el);
            return;
        }

        size++;
        Node<T> curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        Node<T> newNode = new Node<>(el, curr, curr.prev);
        curr.prev.next = newNode;
        curr.prev = newNode;
    }

    /**
     * @return the first element of the list
     * @throws NoSuchElementException
     */
    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException("The list is empty");
        }
        return first.item;
    }

    /**
     * @return the last element of the list
     * @throws NoSuchElementException
     */
    public T getLast() {
        if (last == null) {
            throw new NoSuchElementException("The list is empty");
        }
        return last.item;
    }

    /**
     * @param index index of the element to return
     * @return element at the given index
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) {
        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> curr = first;
        for (int i = 0; i < index; ++i) {
            curr = curr.next;
        }
        return curr.item;
    }

    /**
     * Removes and returns the first element of the list
     * @return the first element of the list
     * @throws NoSuchElementException
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }

        T value = first.item;
        Node<T> oldFirst = first;

        first = oldFirst.next;

        // helping GC
        oldFirst.next = null;
        oldFirst.item = null;

        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;

        return value;
    }

    public T removeLast() {
        return null;
    }

    public T remove(int index) {
        return null;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
