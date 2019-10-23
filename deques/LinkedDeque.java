package deques;

public class LinkedDeque<T> implements Deque<T> {
    private int size;
    private Node head;
    private Node tail;

    public LinkedDeque() {
        size = 0;
        this.head = null;
        this.tail = null;
    }

    private class Node {
        private T value;
        Node next;
        Node prev;
        Node(T value) {
            this.value = value;
        }
    }

    public void addFirst(T item) {
        Node newHead = new Node(item);
        if (size == 0) {
            tail = newHead;
        } else {
            head.prev = newHead;
            newHead.next = head;
        }
        head = newHead;
        size += 1;
    }

    public void addLast(T item) {
        Node newLast = new Node(item);
        if (size == 0) {
            head = newLast;
        } else {
            tail.next = newLast;
            newLast.prev = tail;
        }
        tail = newLast;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = head;
        if (head.next == null) {
            tail = null;
        } else {
            head.next.prev = null;
        }
        head = head.next;
        size -= 1;
        return first.value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = tail;
        // 如果只有一個數
        if (head.next == null) {
            head = null;
        } else {
            tail.prev.next = null;
        }
        tail = tail.prev;
        size -= 1;
        return last.value;
    }

    public T get(int index) {
        if ((index > size) || (index < 0)) {
            return null;
        }
        Node ans = head;
        for (int i = 0; i < index; i++) {
            ans = ans.next;
        }
        return ans.value;
    }


    public int size() {
        return size;
    }
}
