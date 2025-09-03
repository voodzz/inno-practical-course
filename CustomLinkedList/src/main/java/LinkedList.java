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

    public void add(int index, T el) {

    }

    /**
     * @return the first element of the list
     */
    public T getFirst() {
        return first.item;
    }

    /**
     * @return the last element of the list
     */
    public T getLast() {
        return last.item;
    }

    /**
     * @param index index of the element to return
     * @return element at the given index
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> curr = first;
        for (int i = 0; i < index; ++i) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T removeFirst() {
        return null;
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
