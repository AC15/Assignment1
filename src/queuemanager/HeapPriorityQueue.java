package queuemanager;

/**
 * Created by 15009717 on 16/03/2018
 *
 * Implementation of the heap.
 *
 * @param <T> The type of things being stored.
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {
    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size Size of the queue.
     */
    public HeapPriorityQueue(int size) {
        storage = new Object[size];
        storage[0] = 0; // empty element at position zero to simplify calculations
        capacity = size;
        tailIndex = 0;
    }

    /**
     * Returns the head of the queue, which is the second item in the array.
     *
     * @return The item with the highest priority.
     * @throws QueueUnderflowException When queue is empty.
     */
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        return ((PriorityItem<T>) storage[1]).getItem();
    }

    /**
     * Adds an item to the heap.
     *
     * @param item Name of the person to be added.
     * @param priority Priority of the person in the queue.
     * @throws QueueOverflowException When queue is too small.
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        if (tailIndex >= capacity - 1) {
            throw new QueueOverflowException();
        }

        tailIndex++;
        storage[tailIndex] = new PriorityItem<>(item, priority);
        bubbleUp(tailIndex);
    }

    /**
     * Recursive method that places newly added node in the
     * correct place so the heap maintains the max heap property.
     *
     * @param nodeIndex Index of the node that is moved.
     */
    private void bubbleUp(int nodeIndex) {
        // return when there is only one item in the queue
        if (nodeIndex == 1) {
            return;
        }

        int parentIndex = nodeIndex / 2;
        PriorityItem<T> parent = (PriorityItem<T>) storage[parentIndex];
        PriorityItem<T> node = (PriorityItem<T>) storage[nodeIndex];

        // return when parent node is already larger than the child node
        if (parent.getPriority() > node.getPriority()) {
            return;
        }

        // swap child and parent
        swapNodes(nodeIndex, parentIndex);

        bubbleUp(parentIndex);
    }

    /**
     * Removes the highest priority item from the heap.
     * If there are two identical highest priority items
     * only the first one added will be removed.
     *
     * @throws QueueUnderflowException When queue is too small.
     */
    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        // replaces the head of the queue with the last item in the queue
        storage[1] = storage[tailIndex];

        // removes the last item from the queue
        storage[tailIndex] = null;
        tailIndex--;

        bubbleDown(1);
    }

    /**
     * Recursive method that places node from the root of the heap
     * in the correct place so it maintains the max heap property.
     *
     * @param nodeIndex Index of the node that is moved.
     */
    private void bubbleDown(int nodeIndex) {
        int leftChildIndex = 2 * nodeIndex;
        int rightChildIndex = 2 * nodeIndex + 1;
        int largerChildIndex = leftChildIndex;

        // if a node does not have a child return
        if (leftChildIndex > tailIndex) {
            return;
        }

        // if node has a right child, check if it has a larger priority than the left child
        if (rightChildIndex <= tailIndex) {
            int leftChildPriority = ((PriorityItem<T>) storage[leftChildIndex]).getPriority();
            int rightChildPriority = ((PriorityItem<T>) storage[rightChildIndex]).getPriority();

            if (leftChildPriority < rightChildPriority) {
                largerChildIndex = rightChildIndex;
            }
        }

        int parentNodePriority = ((PriorityItem<T>) storage[nodeIndex]).getPriority();
        int smallerChildNodePriority = ((PriorityItem<T>) storage[largerChildIndex]).getPriority();

        // return if parent is already larger than the larger child
        if (parentNodePriority > smallerChildNodePriority) {
            return;
        }

        // swap node with the higher priority child
        swapNodes(nodeIndex, largerChildIndex);
        bubbleDown(largerChildIndex);
    }

    /**
     * Swaps two nodes in the queue.
     *
     * @param firstNodeIndex First node to be swapped.
     * @param secondNodeIndex Second node to be swapped.
     */
    private void swapNodes(int firstNodeIndex, int secondNodeIndex) {
        Object temporaryNode = storage[secondNodeIndex];
        storage[secondNodeIndex] = storage[firstNodeIndex];
        storage[firstNodeIndex] = temporaryNode;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return Returns true when queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return tailIndex == 0;
    }

    /**
     * Creates a string with the contents of the queue.
     *
     * @return A string with the contents of the queue.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 1; i <= tailIndex; i++) {
            if (i > 1) {
                result.append(", ");
            }
            result.append(storage[i]);
        }
        result.append("]");
        return result.toString();
    }
}
