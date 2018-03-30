package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queuemanager.QueueOverflowException;
import queuemanager.UnsortedLinkedPriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 15009717 on 21/03/2018
 *
 * Test for the Unsorted Linked Priority Queue.
 */
class UnsortedLinkedPriorityQueueTest extends PriorityQueueTest {

    @Override
    @BeforeEach
    void setUp() {
        instance = new UnsortedLinkedPriorityQueue<>();
    }

    @Test
    void addANDtoString_AddItemsInRandomOrder_ItemsShouldBeSortedByAddingOrder() throws QueueOverflowException {
        addEightItemsInRandomOrder();

        String expectedResult = "[(3, 3), (8, 8), (6, 6), (7, 7), (5, 5), (4, 4), (1, 1), (2, 2)]";

        assertEquals(expectedResult, instance.toString());
    }
}