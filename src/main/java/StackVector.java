import java.util.Vector;

public class StackVector<E> implements Stack<E> {

    protected Vector<E> data;

    public StackVector() {
        data = new Vector<E>();
    }

    /**
     * Add an item to the stack. Will be popped next.
     *
     * @param item
     */
    @Override
    public void add(E item) {
        data.add(item);
    }

    /**
     * If the Stack is not empty, the most recent item will be removed and returned.
     *
     * @return Item
     */
    @Override
    public E remove() {
        return data.remove(size() - 1);
    }

    /**
     * Get the current Item on the Stack (without popping it).
     *
     * @return Item
     */
    @Override
    public E peek() {
        return data.get(size() - 1);
    }

    /**
     * Check if the Stack is empty.
     *
     * @return Boolean indicating if the Stack is empty
     */
    @Override
    public boolean empty() {
        return size() == 0;
    }

    /**
     * Get the size of the Stack.
     *
     * @return Integer indicating the size of the Stack
     */
    @Override
    public int size() {
        return data.size();
    }
}
