/**
 * Defines all the methods that a Stack should have.
 * @param <E> Type of the items stored on the Stack. Example: String
 */
public interface Stack<E> {
    /**
     * Add an item to the stack. Will be popped next.
     * @param item
     */
    public void add(E item);

    /**
     * If the Stack is not empty, the most recent item will be removed and returned.
     * @return Item
     */
    public E remove();

    /**
     * Get the current Item on the Stack (without popping it).
     * @return Item
     */
    public E peek();

    /**
     * Check if the Stack is empty.
     * @return Boolean indicating if the Stack is empty
     */
    public boolean empty();

    /**
     * Get the size of the Stack.
     * @return Integer indicating the size of the Stack
     */
    public int size();
}