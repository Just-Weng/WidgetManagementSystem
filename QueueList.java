public class QueueList {
    private node front;
    private node rear;
    private int count = 0;

    public QueueList() {
        front = new node();
        rear = front;
    }

    /**
     * Checks if the queue is empty by seeing if the front is null
     * Complexity: O(1)
     * @return
     *          true if front.next == null, else false
     */
    public boolean isEmpty() {
        return (front.next == null);
    }

    /**
     * Enqueues the node with data = x into the end of the queue
     * Complexity: O(1)
     * @param x
     *          data field of node being added
     */
    public void enqueue(int x) {
        rear.next = new node(x);
        if(front == rear) front.next = rear.next; //queue is empty, count == 0
        rear = rear.next;
        count++;
    }


    /**
     * Peek gets the top element of the stack without removing the item
     * Complexity: O(1)
     * @return
     *          the Widget element that is at the first node of the queue list is at
     * @throws ArrayIndexOutOfBoundsException
     */
    public int peek() throws IllegalArgumentException{
        if(isEmpty()) throw new IllegalArgumentException("Stack list is currently empty");
        return front.next.data;
    }

    /**
     * Checks if the queue is empty before attempting dequeue, then returns the element being removed after removing from the queue
     * Complexity: O(1)
     * @return
     *          node being dequeued
     * @throws IllegalArgumentException
     *                                  if the queue is empty before attempting dequeue
     */
    public int dequeue() throws IllegalArgumentException {
        if(isEmpty()) throw new IllegalArgumentException("Queue is currently empty");
        int oldFront = front.next.data;
        front.next = front.next.next;
        count--;
        if(count == 0) rear = front;
        return oldFront;
    }

    /**
     * String representation of the QueueList object
     * @return
     *          String representing QueueList
     */
    public String toString() {
        String queue = "";
        node current = front.next;
        while(current != null) {
            queue += current.data + " ";
            current = current.next;
        }
        return queue;
    }
}