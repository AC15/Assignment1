package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queuemanager.QueueOverflowException;
import queuemanager.SortedLinkedPriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 15009717 on 21/03/2018
 *
 * Test for the Sorted Linked Priority Queue.
 */
class SortedLinkedPriorityQueueTest extends PriorityQueueTest {

    @Override
    @BeforeEach
    void setUp() {
        instance = new SortedLinkedPriorityQueue<>();
    }

    @Test
    void addANDtoString_AddItemsInRandomOrder_ItemsShouldBeSortedInDescendingOrder() throws QueueOverflowException {
        addEightItemsInRandomOrder();

        String expectedResult = "[(8, 8), (7, 7), (6, 6), (5, 5), (4, 4), (3, 3), (2, 2), (1, 1)]";

        assertEquals(expectedResult, instance.toString());
    }
}