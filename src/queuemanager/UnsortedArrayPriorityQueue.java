package queuemanager;

/**
 * Created by 15009717 on 13/03/2018
 *
 * Implementation of the Unsorted Array Priority Queue.
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {

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
    UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
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

        // highestPriority is set to minimum integer value, because priority can be set to negative values
        int highestPriority = Integer.MIN_VALUE;
        int highestPriorityIndex = 0;

        for (int i = 0; i <= tailIndex; i++) {
            int currentItemPriority = ((PriorityItem<T>) storage[i]).getPriority();

            if (currentItemPriority > highestPriority) {
                highestPriority = currentItemPriority;
                highestPriorityIndex = i;
            }
        }

        return ((PriorityItem<T>) storage[highestPriorityIndex]).getItem();
    }

    /**
     * Items are added in order of arrival.
     *
     * @param item Name of the person to be added.
     * @param priority Priority of the person in the queue.
     * @throws QueueOverflowException When queue is too small.
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            throw new QueueOverflowException();
        }

        tailIndex++;
        storage[tailIndex] = new PriorityItem<>(item, priority);
    }

    /**
     * Removes the highest priority item from the queue.
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

        T elementToBeRemoved = head();

        for (int i = 0; i <= tailIndex; i++) {
            PriorityItem<T> currentItem = (PriorityItem<T>) storage[i];

            if (elementToBeRemoved == currentItem.getItem()) {
                System.arraycopy(storage, i + 1, storage, i, tailIndex - i);
                tailIndex--;
                break;
            }
        }
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return Returns true when queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    /**
     * Creates a string with the contents of the queue.
     *
     * @return A string with the contents of the queue.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(storage[i]);
        }
        result.append("]");
        return result.toString();
    }
}