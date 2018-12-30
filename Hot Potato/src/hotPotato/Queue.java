package hotPotato;

import java.util.ArrayList;

/**
 * <h1>Queue</h1>
 * 
 * @author Shalin A. Patel
 * @since April 3rd, 2018
 *        <p>
 *        The basic code for the 'queue' Abstract Data Type. This programs
 *        includes the basic functions such as enqueue, dequeue, peek, and
 *        isEmpty.
 */
public class Queue<E> {
	ArrayList<E> queue;

	/**
	 * Constructor for a new queue variable.
	 */
	public Queue() {
		queue = new ArrayList<E>();
	}

	/**
	 * Add an element to the queue.
	 * 
	 * @param element
	 *            The element that the user wishes to add to queue.
	 */
	public void enqueue(E element) {
		queue.add(element);
	}

	/**
	 * Remove the first element in the queue.
	 * 
	 * @return newValue The first element in the queue that was removed.
	 */
	public E dequeue() {
		// code to pop (i.e. remove and return) an element
		// from the end of the stack goes here
		boolean empty = isEmpty();
		if (!empty) {
			E newValue = queue.get(0);
			queue.remove(0);
			return newValue;
		}
		return null;
	}
	
	public E dequeue(int where) {
		boolean empty = isEmpty();
		if (!empty) {
			E newValue = queue.get(where);
			queue.remove(where);
			return newValue;
		}
		return null;
	}
	
	public void dequeue(String what) {
		boolean empty = isEmpty();
		if (!empty) {
			queue.remove(what);
		}
	}

	/**
	 * This function allows the user to view the first element in the stack.
	 * 
	 * @return newValue The variable that is at the beginning of the queue (the
	 *         first element).
	 */
	public E peek() {
		// code to view, but not remove the element from
		// the end of the stack goes here
		boolean empty = isEmpty();
		if (!empty) {
			E newValue = queue.get(0);
			return newValue;
		}
		return null;
	}

	public int length() {
		if (!isEmpty()) {
			int length = queue.size();
			return length;
		}
		return 0;
	}

	public boolean contains(String element) {
		if (queue.contains(element)){
			return true;
		}
		return false;
	}
	
	public E peek(int index) {
		// code to view, but not remove the element from
		// the end of the stack goes here
		boolean empty = isEmpty();
		if (!empty) {
			E newValue = queue.get(index);
			return newValue;
		}
		return null;
	}

	/**
	 * This function checks if the stack is empty or not. It returns true if the
	 * stack is empty.
	 */
	public boolean isEmpty() {
		// code to check if stack is empty goes here
		int size = queue.size();
		if (size == 0) {
			return true;
		}
		return false;
	}

	public int getIndex(String element) {
		int value = queue.indexOf(element);
		return value;
	}

}
