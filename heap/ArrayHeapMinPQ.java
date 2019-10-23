package heap;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;



public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> arrayHeap;
    private HashMap<T, Integer> record; // record key - index in arraylist
    private int size = 0;

    private  class PriorityNode {
        private T key;
        private double priority;
        PriorityNode(T k, double p) {
            this.key = k;
            this.priority = p;
        }

        T getKey() {
            return key;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double newpri) {
            this.priority = newpri;
        }

    }

    public ArrayHeapMinPQ() {
        arrayHeap = new ArrayList<>();
        record = new HashMap<>();

    }

    /*
    Here's a helper method and a method stub that may be useful. Feel free to change or remove
    them, if you wish.
     */



    /**
     * Adds an item with the given priority value.
     * Assumes that item is never null.
     * Runs in O(log N) time (except when resizing).
     * @throws IllegalArgumentException if item is already present in the PQ
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("This key already exists.");
        }
        arrayHeap.add(new PriorityNode(item, priority));
        record.put(item, size);

        shiftUp(size);
        size++;

    }

    /**
     * Returns true if the PQ contains the given item; false otherwise.
     * Runs in O(log N) time.
     */
    @Override
    public boolean contains(T item) {
        return record.containsKey(item);
    }

    /**
     * Returns the item with the smallest priority.
     * Runs in O(log N) time.
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty now.");
        }
        return arrayHeap.get(0).getKey();
    }

    /**
     * Removes and returns the item with the smallest priority.
     * Runs in O(log N) time (except when resizing).
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty now.");
        }

        T removeNode = arrayHeap.get(0).getKey();
        swap(0, size - 1);
        record.remove(arrayHeap.get(size - 1).getKey());
        arrayHeap.remove(size - 1);
        size--;
        shiftDown(0);



        return removeNode;
    }

    /**
     * Changes the priority of the given item.
     * Runs in O(log N) time.
     * @throws NoSuchElementException if the item is not present in the PQ
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("This key is not in PQ.");
        }
        double oldPriority = arrayHeap.get(record.get(item)).getPriority();
        arrayHeap.get(record.get(item)).setPriority(priority);

        if (oldPriority > priority) {
            shiftUp(record.get(item));
        }

        if (oldPriority < priority) {
            shiftDown(record.get(item));
        }
    }

    /**
     * Returns the number of items in the PQ.
     * Runs in O(log N) time.
     */
    @Override
    public int size() {
        return size;
    }

    // Helper methoods

    private boolean greater(int i, int j) {
        return arrayHeap.get(i).getPriority() > arrayHeap.get(j).getPriority();
    }

    private void shiftUp(int ele) {
        while (ele > 0) {
            int parentIndex = (ele - 1) / 2;
            if (greater(parentIndex, ele)) {
                swap(ele, parentIndex);
            }
            ele = parentIndex;

        }
        return;

    }

    private void shiftDown(int ele) {
        while (ele * 2 + 1 < size) {
            int childIndex = ele * 2 + 1;
            if (childIndex + 1 < size && greater(childIndex, childIndex + 1)) {
                childIndex = childIndex + 1;  // 在左右子樹找一個小的交換
            }
            if (greater(childIndex, ele)) {
                break;
            }
            swap(ele, childIndex);
            ele = childIndex;

        }
    }



    /**
     * A helper method to create arrays of T, in case you're using an array to represent your heap.
     * You shouldn't need this if you're using an ArrayList instead.
     */
    /*
    @SuppressWarnings("unchecked")
    private T[] makeArray(int newCapacity) {
        return (T[]) new Object[newCapacity];
    }
    */


    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        // change position a, b
        PriorityNode temp = arrayHeap.get(a);
        arrayHeap.set(a, arrayHeap.get(b));
        arrayHeap.set(b, temp);
        // change index key-index
        record.put(arrayHeap.get(a).getKey(), a);
        record.put(arrayHeap.get(b).getKey(), b);
    }
}
