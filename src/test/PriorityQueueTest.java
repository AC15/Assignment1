package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queuemanager.Person;
import queuemanager.PriorityQueue;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by AC15 on 21/03/2018
 */
abstract class PriorityQueueTest {
    PriorityQueue<Person> instance;

    @BeforeEach
    abstract void setUp();

    @Test
    void add_AddAPersonToQueue_ShouldBeAddedToHead() throws QueueOverflowException, QueueUnderflowException {
        Person person = new Person("Alex");
        instance.add(person, 20);
        assertEquals(person.getName(), instance.head().getName());
    }

    @Test
    void add_AddNegativePriorityItem_ShouldBeAddedToHead() throws QueueOverflowException, QueueUnderflowException {
        Person person = new Person("Alex");
        instance.add(person, -20);
        assertEquals(person.getName(), instance.head().getName());
    }

    @Test
    void head_WhenQueueHasOneElement_ShouldReturnHead() throws QueueOverflowException, QueueUnderflowException {
        addNewPerson("Alex", 20);
        assertEquals("Alex", instance.head().getName());
    }

    @Test
    void head_WhenQueueHasTwoElements_ShouldReturnHead() throws QueueOverflowException, QueueUnderflowException {
        addNewPerson("Alex", 20);
        addNewPerson("John", 50);
        assertEquals("John", instance.head().getName());
    }

    @Test
    void head_WhenQueueIsEmpty_ShouldThrowException() throws QueueUnderflowException {
        assertThrows(QueueUnderflowException.class, ()-> instance.head());
    }

    @Test
    void remove_WhenQueueHasOneElement_HeadElementShouldBeRemoved() throws QueueOverflowException, QueueUnderflowException {
        addNewPerson("Alex", 20);
        instance.remove();
        assertTrue(instance.isEmpty());
    }

    @Test
    void remove_WhenQueueHasTwoElements_HeadElementShouldBeRemoved() throws QueueOverflowException, QueueUnderflowException {
        addNewPerson("John", 50);
        addNewPerson("Alex", 20);
        instance.remove();
        assertEquals("Alex", instance.head().getName());
    }

    @Test
    void remove_WhenQueueIsEmpty_ShouldThrowException() throws QueueOverflowException, QueueUnderflowException {
        assertThrows(QueueUnderflowException.class, ()-> instance.remove());
    }

    @Test
    void isEmpty_WhenQueueIsEmpty_ShouldReturnTrue() {
        assertTrue(instance.isEmpty());
    }

    @Test
    void isEmpty_WhenQueueIsNotEmpty_ShouldReturnFalse() throws QueueOverflowException {
        addNewPerson("Alex", 20);
        assertFalse(instance.isEmpty());
    }

    @Test
    void toString_WhenQueueHasOneElement_ShouldDisplayWithoutComma() throws QueueOverflowException {
        addNewPerson("Alex", 20);
        assertEquals("[(Alex, 20)]", instance.toString());
    }

    @Test
    void toString_WhenQueueIsEmpty_ShouldReturnEmptyQueue() {
        assertEquals("[]", instance.toString());
    }

    void addNewPerson(String personName, int priority) throws QueueOverflowException {
        Person person = new Person(personName);
        instance.add(person, priority);
    }

    void addEightItemsInRandomOrder() throws QueueOverflowException {
        addNewPerson("2", 2);
        addNewPerson("1", 1);
        addNewPerson("4", 4);
        addNewPerson("5", 5);
        addNewPerson("7", 7);
        addNewPerson("6", 6);
        addNewPerson("8", 8);
        addNewPerson("3", 3);
    }
}