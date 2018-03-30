package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queuemanager.QueueOverflowException;
import queuemanager.SortedArrayPriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 15009717 on 21/03/2018
 *
 * Test for the Sorted Array Priority Queue.
 */
class SortedArrayPriorityQueueTest extends PriorityQueueTest {

    @Override
    @BeforeEach
    void setUp() {
        instance = new SortedArrayPriorityQueue<>(8);
    }

    @Test
    void addANDtoString_AddItemsInRandomOrder_ItemsShouldBeSortedInDescendingOrder() throws QueueOverflowException {
        addEightItemsInRandomOrder();

        String expectedResult = "[(8, 8), (7, 7), (6, 6), (5, 5), (4, 4), (3, 3), (2, 2), (1, 1)]";

        assertEquals(expectedResult, instance.toString());
    }

    @Test
    void add_WhenQueueIsFull_ShouldThrowException() throws QueueOverflowException {
        addEightItemsInRandomOrder();
        assertThrows(QueueOverflowException.class, ()-> addNewPerson("Alex", 20));
    }
}