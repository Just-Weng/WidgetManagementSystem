public class StackArray {
    private Widget[] data;
    private int top = -1;
    private int maxSize = 10;

    public StackArray() {data = new Widget[maxSize];}

    public StackArray(int size) {
        maxSize = size;
        data = new Widget[maxSize];
    }

    /**
     * isEmpty checks if the stack is currently empty via the top pointer on the StackArray
     * Complexity: O(1)
     * @return
     *          true/false if top == -1
     */
    public boolean isEmpty() {return top == -1;}

    /**
     * If stack is full, increases the size of the array, copy the elements into the new array.
     * Then push the element into the stack, by inserting the next element into the StackArray.
     * Complexity: O(n) if full, O(1) if not
     * @param x
     *          integer item to be added into the stack
     */
    public void push(Widget x) {
        if(top == maxSize - 1) {
            Widget[] temp = new Widget[maxSize * 2];
            for(int i = 0; i < maxSize; i++) {
                temp[i] = data[i];
            }
            maxSize *= 2;
            data = temp;
        }
        data[++top] = x;
    }

    /**
     * Peek gets the top element of the stack without removing the item
     * Complexity: O(1)
     * @return
     *          the widget element that is at the index top is at
     */
    public Widget peek() throws ArrayIndexOutOfBoundsException{
        if(isEmpty()) throw new ArrayIndexOutOfBoundsException("Index -1 is Out of Bounds");
        return data[top];
    }

    /**
     * Pop removes the top element of the array, then shifts the top to the new top element
     * Complexity: O(1)
     * @return
     *          the top element being removed
     */
    public Widget pop() throws ArrayIndexOutOfBoundsException {
        if(isEmpty()) throw new ArrayIndexOutOfBoundsException("Index -1 is Out of Bounds");
        return data[top--];
    }

    /**
     * returns the String representation of the stack
     * @return
     *          String representation of the stack object
     */
    public String toString() {
        String stack = "";
        for(int i = 0; i <= top; i++) {
            stack += data[i] + " ";
        }
        return stack;
    }
}