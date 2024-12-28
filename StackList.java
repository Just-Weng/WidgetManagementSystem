public class StackList {
    private node top;

    /**
     * isEmpty checks if the stack is currently empty via the top node
     * Complexity: O(1)
     * @return
     *          true/false if top == null (the end of the list)
     */
    public boolean isEmpty() {return top == null;}

    /**
     * Increases the list with a new node, and sets top as the new node
     * Complexity: O(1)
     * @param x
     *          integer item to be added into the stack
     */
    public void push(int x) {
        top = new node(x, top);
    }

    /**
     * Peek gets the top element of the stack without removing the item
     * Complexity: O(1)
     * @return
     *          the data portion of the top node
     */
    public int peek() throws IllegalArgumentException{
        if(isEmpty()) throw new IllegalArgumentException("Stack list is currently empty");
        return top.data;
    }

    /**
     * Pop removes the top element of the array, then shifts the top to the new top element
     * Complexity: O(1)
     * @return
     *          the top element being removed
     */
    public int  pop() throws IllegalArgumentException {
        if(isEmpty()) throw new IllegalArgumentException("Stack list is currently empty");
        int oldTop = top.data;
        top = top.next;
        return oldTop;
    }

    /**
     * String representation of the StackList object
     * @return
     *          String representing StackList
     */
    public String toString() {
        String stack = "";
        node current = top;
        while(current != null) {
            stack += current.data + " ";
            current = current.next;
        }
        return stack;
    }
}