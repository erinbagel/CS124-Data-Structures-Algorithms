
//Erin Walter - Assignment 7 due October 12 2016
//Running Time analysis: θ(n) 
//All tests passed 
import java.util.*;

/** QueueLi implements queues using linked lists. **/
public class QueueLi<AnyType> {

	private Node<AnyType> header;
	private Node<AnyType> back;

	/** Construct an empty queue **/
	public QueueLi() {
		header = back = new Node<AnyType>(null, null);

	}

	private static class Node<AnyType> {
		AnyType data;
		Node<AnyType> next;

		public Node(AnyType theData, Node<AnyType> theNext) {
			data = theData;
			next = theNext;
		}
	}

	/**
	 * Assigns the new front position to the node.
	 * 
	 * @param x
	 */
	public void enqueue(AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x, null);
		back.next = newNode;
		back = newNode;
		// Running time is θ(1) because it's a constant operation.
	}

	/**
	 * Returns the data in the front position of the queue.
	 * 
	 * @return header.next.data
	 */
	public AnyType getFront() {
		if (empty()) {
			return null;
		}
		return header.next.data;
		// Running time is θ(1) because it's a constant operation.
	}

	/**
	 * Assigns the new back position to the node.
	 * 
	 * @return the old header
	 */
	public AnyType dequeue() {
		if (empty()) {
			return null;
		}
		AnyType prevHeader = header.next.data; // assign back to header
		header.next = header.next.next;
		if (empty()) {
			back = header.next;
		}
		return prevHeader; // if not return old header, check to see if removed
		// Running time is θ(1) because it's a constant operation.
	}

	/**
	 * Checks to see if the queue is empty.
	 * 
	 * @return true if empty, otherwise return false.
	 */
	public boolean empty() {

		return header.next == null;
		// Running time is θ(1) because it's a constant operation.

	}
}

class TestQueueLi {
	public static void main(String[] args) {
		QueueLi<Integer> li = new QueueLi<Integer>();
		for (int i = 4; i <= 10; i++)
			li.enqueue(i);
		System.out.println("My getFront(): " + li.getFront());
		System.out.println("My dequeue(): " + li.dequeue());
		System.out.println("My dequeue(): " + li.dequeue());
		System.out.println("My dequeue(): " + li.dequeue());
		System.out.println("My getFront(): " + li.getFront());
		System.out.println("My empty(): " + li.empty());

	}
}

// OUTPUT:
// My getFront(): 4
// My dequeue(): 4
// My dequeue(): 5
// My dequeue(): 6
// My getFront(): 7
// My empty(): false
