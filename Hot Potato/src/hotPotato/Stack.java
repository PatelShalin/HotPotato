package hotPotato;

import java.util.ArrayList;

/**
 * <h1>Stack</h1>
 * 
 * @author Shalin A. Patel
 * @since April 3rd, 2018
 *        <p>
 * 		The basic code for the 'stack' Abstract Data Type. This programs
 *        includes the basic functions such as push, pop, peek, and isEmpty.
 */

public class Stack<E> {
	ArrayList<E> stack;

	/**
	 * Constructor for a new stack variable.
	 */
	public Stack() {
		stack = new ArrayList<E>();
	}

	/**
	 * This function adds an element to the end of the arrayList 'stack'
	 * 
	 * @param element
	 *            The element that the user wishes to pass into the stack.
	 */
	public void push(E element) {
		stack.add(element);
	}
	
	public int length() {
		int length = stack.size();
		return length;
	}

	/**
	 * This function removes or 'pops' the last element that was added or 'pushed'
	 * into the stack. This element is also returned to the user.
	 * 
	 * @return newValue The variable that was popped.
	 */
	public E pop() {
		// code to pop (i.e. remove and return) an element
		// from the end of the stack goes here
		boolean empty = isEmpty();
		if (!empty) {
			int index = stack.size() - 1;
			E newValue = stack.get(index);
			stack.remove(index);
			return newValue;
		}
		return null;
	}

	/**
	 * This function allows the user to view the most recent added element in the
	 * stack.
	 * 
	 * @return newValue The variable that is at the top of the stack.
	 */
	public E peek() {
		// code to view, but not remove the element from
		// the end of the stack goes here
		boolean empty = isEmpty();
		if (!empty) {
			int index = stack.size() - 1;
			E newValue = stack.get(index);
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
		int size = stack.size();
		if (size == 0) {
			return true;
		}
		return false;
	}
}
