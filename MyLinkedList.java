//Erin Walter - Assignment 7 due October 12 2016
//Running Time for contains method: O(n) best case it is found immediately,
//worst case it is not found. 
//Contains method starts on line 62
//All test passed

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	/**
	 * Construct an empty LinkedList.
	 */
	public MyLinkedList() {
		doClear();
	}

	private void clear() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *            any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * Check each value through the list for current value. Running time is
	 * O(n).
	 * 
	 * @param x
	 * @return true if the value exists, and false otherwise.
	 */
	public boolean contains(AnyType x) {
		Node<AnyType> currentVal = beginMarker.next;
		while (currentVal != endMarker) {
			if (currentVal.data.equals(x)) {
				return true;
			} else {
				currentVal = currentVal.next;
			}
		}
		return false;
	}

	/**
	 * Adds a value x to the first position.
	 * 
	 * @param x
	 *            Running time is constant. θ(1)
	 */
	public void addFirst(AnyType x) {
		add(0, x);
	}

	/**
	 * Adds a value x to the last position.
	 * 
	 * @param x
	 *            Running time is θ(1).
	 */
	public void addLast(AnyType x) {
		add(size(), x);
	}

	/**
	 * Removes the value at first position. Running time is θ(1) because it is
	 * constant.
	 */
	public void removeFirst() {
		remove(0);
	}

	/**
	 * Removes the value at the last position. Running time is θ(1) because it
	 * is constant.
	 */
	public void removeLast() {
		remove(size() - 1);
	}

	/**
	 * Gets the first element. Running time is θ(1) because it is constant.
	 * 
	 * @return
	 */
	public AnyType getFirst() {
		return get(0);
	}

	/**
	 * Gets the last element. Running time is θ(1) because it is constant.
	 * 
	 * @return
	 */
	public AnyType getLast() {
		return get(size() - 1);
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x
	 *            any object.
	 * @param idx
	 *            position to add at.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at specified position p. Items at or
	 * after that position are slid one position higher.
	 * 
	 * @param p
	 *            Node to add before.
	 * @param x
	 *            any object.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *            the index to search in.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *            the index to change.
	 * @param newVal
	 *            the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *            index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *            index to search at.
	 * @param lower
	 *            lowest valid index.
	 * @param upper
	 *            highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *            the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *            the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;

		return p.data;
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (AnyType x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a
	 * notion of a current position and of course the implicit reference to the
	 * MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
		}

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}

	private int theSize;
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
}

class TestLinkedList {
	public static void main(String[] args) {
		MyLinkedList<Integer> lst = new MyLinkedList<>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		lst.remove(0);
		lst.remove(lst.size() - 1);

		System.out.println(lst);

		System.out.println("Is 4 in the list?");
		System.out.println(lst.contains(4));
		System.out.println("Get the first value in the list:");
		System.out.println(lst.getFirst());
		System.out.println("Get the last value in the list: ");
		System.out.println(lst.getLast());
		;

		lst.removeFirst();
		System.out.println("List with first removed: " + lst);
		lst.removeLast();
		System.out.println("List with last removed: " + lst);
		lst.addFirst(2);
		System.out.println("List with 2 added first: " + lst);
		lst.addLast(9);
		System.out.println("List with 9 added last: " + lst);
	}
}

// OUTPUT:
// [ 28 27 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 8 ]
// Is 4 in the list?
// true
// Get the first value in the list:
// 28
// Get the last value in the list:
// 8
// List with first removed: [ 27 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 8 ]
// List with last removed: [ 27 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 ]
// List with 2 added first: [ 2 27 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 ]
// List with 9 added last: [ 2 27 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 9 ]