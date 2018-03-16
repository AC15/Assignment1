package queuemanager;

/**
 * Created by AC15 on 15/03/2018
 */
class ListNode<T> {
    private PriorityItem<T>  item;
    private ListNode<T> next;

    ListNode(PriorityItem<T> item, ListNode<T> next) {
        this.item = item;
        this.next = next;
    }

    /**
     * Returns the PriorityItem stored in the node.
     *
     * @return Returns a PriorityItem stored in the node.
     */
    PriorityItem<T> getItem() {
        return item;
    }

    /**
     * Returns the next node.
     *
     * @return Returns the next node.
     */

    ListNode<T> getNext() {
        return next;
    }

    /**
     * Changes the pointer to a different node.
     *
     * @param nextNode New node that this node will point to.
     */
    void setNext(ListNode<T> nextNode) {
        next = nextNode;
    }
}

