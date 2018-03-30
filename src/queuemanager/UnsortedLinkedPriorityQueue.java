package queuemanager;

/**
 * Created by 15009717 on 15/03/2018
 *
 * Implementation of the Unsorted Linked List Priority Queue.
 *
 * @param <T> The type of things being stored.
 */
public class UnsortedLinkedPriorityQueue<T> implements PriorityQueue<T> {
    /**
     * Where the data is actually stored.
     */
    private ListNode<T> top;

    public UnsortedLinkedPriorityQueue() {
        this.top = null;
    }

    /**
     * Searches the queue for the highest priority item and returns it.
     *
     * @return The item with the highest priority.
     * @throws QueueUnderflowException When queue is empty.
     */
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        ListNode<T> highestPriorityItem = top;

        for (ListNode<T> node = top; node != null; node = node.getNext()) {
            int currentNodePriority = node.getItem().getPriority();
            int highestPriorityItemPriority = highestPriorityItem.getItem().getPriority();

            if (currentNodePriority > highestPriorityItemPriority) {
                highestPriorityItem = node;
            }
        }

        return highestPriorityItem.getItem().getItem();
    }

    /**
     * Adds an item to the front of the queue.
     *
     * @param item Item to be added.
     * @param priority Priority of the item in the queue.
     */
    @Override
    public void add(T item, int priority) {
        PriorityItem<T> newItem = new PriorityItem<>(item, priority);
        top = new ListNode<>(newItem, top);
    }

    /**
     * Removes the highest priority item from the queue.
     * If there are two identical highest priority items
     * only the first one added will be removed.
     *
     * @throws QueueUnderflowException When queue is empty.
     */
    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        T head = head();

        // if the first node is the head, delete it and return from the function.
        if (top.getItem().getItem() == head) {
            top = top.getNext();
            return;
        }

        ListNode<T> currentNode = top;
        ListNode<T> previousNode = null;

        while (currentNode != null && currentNode.getItem().getItem() != head) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        // delete the head node
        previousNode.setNext(currentNode.getNext());
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return Returns true when queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Creates a string with the contents of the queue.
     *
     * @return A string with the contents of the queue.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (ListNode<T> node = top; node != null; node = node.getNext()) {
            if (node != top) {
                result.append(", ");
            }

            result.append(node.getItem());
        }

        result.append("]");

        return result.toString();
    }
}