public class QueueArray {
    Widget[] data;
    int front = 0;
    int rear = -1;
    int count = 0;
    int maxSize = 10;

    public QueueArray() {
        data = new Widget[maxSize];
    }

    /**
     * Checks if the queue is empty
     * Complexity: O(1)
     * @return
     *          true if count == 0, else false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * If queue is full, resizes the array, then copies the elements into the new array.
     * Then enqueues the element into the queue, by inserting the next element into the QueueArray
     * Complexity: O(n) if full, O(1) if not
     * @param x
     *          integer being added into the QueueArray
     */
    public void enqueue(Widget x) {
        if(count == maxSize) {
            Widget[] temp = new Widget[maxSize * 2];
            int j = front;
            for(int i = 0; i < count; i++) {
                temp[i] = data[j];
                j = (j + 1) % maxSize;
            }
            front = 0;
            rear = count - 1;
            maxSize *= 2;
            data = temp;
        }
        rear = (rear + 1) % maxSize;
        data[rear] = x;
        count++;
    }

    /**
     * Peek gets the top element of the stack without removing the item
     * Complexity: O(1)
     * @return
     *          the Widget element that is at the index front is at
     * @throws ArrayIndexOutOfBoundsException
     */
    public Widget peek() throws ArrayIndexOutOfBoundsException{
        if(isEmpty()) throw new ArrayIndexOutOfBoundsException("Index -1 is Out of Bounds");
        return data[front];
    }

    /**
     * Checks if the queue is empty before attempting dequeue, then returns the element being removed after removing from the queue
     * Complexity: O(1)
     * @return
     *          node being dequeued
     * @throws IllegalArgumentException
     *                                  if the queue is empty before attempting dequeue
     */
    public Widget dequeue() throws IllegalArgumentException {
        if (isEmpty()) throw new IllegalArgumentException("Queue is currently empty");
        Widget oldFront = data[front];
        count -= 1;
        front = (front + 1) % maxSize;
        return oldFront;
    }

    /**
     * String representation of the QueueArray object
     * @return
     *          String representing QueueArray
     */
    public String toString() {
        String queue = "";
        for(int i = 0; i < count; i++) {
            queue += data[(front + i) % maxSize] + " ";
        }
        return queue;
    }
}