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
        Node<T> tmp = first;
        first = new Node<>(el, tmp, null);
        if (tmp == null) {
            last = first;
        } else {
            tmp.prev = first;
        }
    }

    public void addLast(T el) {
    }

    public void add(int index, T el) {
    }

    public T getFirst() {
        return null;
    }

    public T getLast() {
        return null;
    }

    public T get(int index) {
        return null;
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
