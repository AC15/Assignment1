package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queuemanager.HeapPriorityQueue;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by AC15 on 21/03/2018
 */
class HeapPriorityQueueTest extends PriorityQueueTest {

    @Override
    @BeforeEach
    void setUp() {
        instance = new HeapPriorityQueue<>(11);
    }

    @Test
    void addANDtoString_AddItemsInRandomOrder_ItemsShouldBeSortedInMaxHeapOrder() throws QueueOverflowException {
        createHeapForTesting();

        String expectedResult = "[(44, 44), (42, 42), (35, 35), (33, 33), (31, 31), (19, 19), (27, 27), (10, 10), (26, 26), (14, 14)]";

        assertEquals(expectedResult, instance.toString());
    }

    @Test
    void add_WhenHeapIsFull_ShouldThrowException() throws QueueOverflowException {
        createHeapForTesting();
        assertThrows(QueueOverflowException.class, ()-> addNewPerson("Alex", 20));
    }

    @Test
    void remove_WhenQueueIsFull_HeadShouldBeRemoved() throws QueueOverflowException, QueueUnderflowException {
        createHeapForTesting();
        instance.remove();

        String expectedResult = "[(42, 42), (33, 33), (35, 35), (26, 26), (31, 31), (19, 19), (27, 27), (10, 10), (14, 14)]";

        assertEquals(expectedResult, instance.toString());
    }

    // based on heap example from https://www.tutorialspoint.com/data_structures_algorithms/heap_data_structure.htm
    private void createHeapForTesting() throws QueueOverflowException {
        addNewPerson("35", 35);
        addNewPerson("33", 33);
        addNewPerson("42", 42);
        addNewPerson("10", 10);
        addNewPerson("14", 14);
        addNewPerson("19", 19);
        addNewPerson("27", 27);
        addNewPerson("44", 44);
        addNewPerson("26", 26);
        addNewPerson("31", 31);
    }
}