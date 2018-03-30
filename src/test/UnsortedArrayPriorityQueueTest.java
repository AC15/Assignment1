package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queuemanager.QueueOverflowException;
import queuemanager.UnsortedArrayPriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 15009717 on 21/03/2018
 *
 * Test for the Unsorted Array Priority Queue.
 */
class UnsortedArrayPriorityQueueTest extends PriorityQueueTest {

    @Override
    @BeforeEach
    void setUp() {
        instance = new UnsortedArrayPriorityQueue<>(8);
    }

    @Test
    void addANDtoString_AddItemsInRandomOrder_ItemsShouldBeSortedByAddingOrder() throws QueueOverflowException {
        addEightItemsInRandomOrder();

        String expectedResult = "[(2, 2), (1, 1), (4, 4), (5, 5), (7, 7), (6, 6), (8, 8), (3, 3)]";

        assertEquals(expectedResult, instance.toString());
    }

    @Test
    void add_WhenQueueIsFull_ShouldThrowException() throws QueueOverflowException {
        addEightItemsInRandomOrder();
        assertThrows(QueueOverflowException.class, () -> addNewPerson("Alex", 20));
    }
}