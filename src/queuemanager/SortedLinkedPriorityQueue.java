package queuemanager;

/**
 * Created by AC15 on 16/03/2018
 *
 * Implementation of the Sorted Linked List Priority Queue.
 */
public class SortedLinkedPriorityQueue<T> implements PriorityQueue<T> {
    /**
     * The top node of the list.
     */
    private ListNode<T> top;

    public SortedLinkedPriorityQueue() {
        this.top = null;
    }

    /**
     * Returns the head of the queue, which is the first item in the list.
     *
     * @return The item with the highest priority.
     * @throws QueueUnderflowException When queue is empty.
     */
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        return top.getItem().getItem();
    }

    /**
     * Adds an item to the queue in an appropriate place.
     *
     * @param item     Item to be added.
     * @param priority Priority of the item in the queue.
     */
    @Override
    public void add(T item, int priority) {
        PriorityItem<T> newItem = new PriorityItem<>(item, priority);

        // if queue is empty or new item has larger priority than the head
        // add the new item to the top
        if (isEmpty() || top.getItem().getPriority() < priority) {
            top = new ListNode<>(newItem, top);
            return;
        }

        ListNode<T> currentNode = top;

        // while current node is not the last item in the queue
        // and current node has larger priority than the node that is being added
        while (currentNode.getNext() != null &&
                priority < currentNode.getNext().getItem().getPriority()) {
            currentNode = currentNode.getNext();
        }

        // create a new node and add it to the current's node next position
        ListNode<T> newNode = new ListNode<>(newItem, currentNode.getNext());
        currentNode.setNext(newNode);
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

        top = top.getNext();
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