// Erin Walter
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	// public void insert(AnyType x) {
	// root = insert(x, root);
	// }

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(AnyType x) {
		root = remove(x, root);
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin() {
		if (isEmpty())
			throw new UnderflowException();
		return findMin(root).element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax() {
		if (isEmpty())
			throw new UnderflowException();
		return findMax(root).element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return true if not found.
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	// public void find(AnyType x)
	// {

	// }

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	/**
	 * Print the tree contents in preorder recursively.
	 */
	public void printTreePreOrder() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTreePreOrder(root);
	}

	/**
	 * Print the tree contents in postorder recursively.
	 */
	public void printTreePostOrder() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTreePostOrder(root);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	// private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
	// if (t == null)
	// return new BinaryNode<>(x, null, null);

	// int compareResult = x.compareTo(t.element);

	// if (compareResult < 0)
	// t.left = insert(x, t.left);
	// else if (compareResult > 0)
	// t.right = insert(x, t.right);
	// else
	// ; // Duplicate; do nothing
	// return t;
	// }
	/**
	 * 
	 * @param x
	 */
	public void insert(AnyType x) {
		BinaryNode<AnyType> newNode = new BinaryNode<>(x, null, null);
		if (root == null) {
			root = newNode;
		}
		BinaryNode<AnyType> t = root;
		int compareResult = x.compareTo(t.element);
		while (compareResult != 0) {
			if (compareResult < 0) {

				if (t.left == null) {
					t.left = newNode;
				} else
					t = t.left;
				compareResult = x.compareTo(t.element);
			} else {
				if (compareResult > 0) {
					if (t.right == null) {
						t.right = newNode;

					} else
						t = t.right;
					compareResult = x.compareTo(t.element);
				}
			}
		}
	}

	/**
	 * 
	 * @return number of nodes in the tree.
	 */
	public int nodes() {
		return nodes(root);
	}

	/**
	 * 
	 * @param count
	 * @param t
	 * @return the number of nodes in tree. Running time is ϴ(n) because every
	 *         node is counted once.
	 */
	private int nodes(BinaryNode<AnyType> t) {
		if (t == null) {
			return 0;
		}
		return 1 + nodes(t.left) + nodes(t.right);

	}

	public int leaves() {
		return leaves(root);
	}

	/**
	 * Running time is ϴ(n), because each leaf is counted once.
	 * 
	 * @param x
	 * @param t
	 * @return the number of leaves in tree.
	 */
	private int leaves(BinaryNode<AnyType> t) {
		if (t == null) {
			return 0;
		}
		if (t.left == null && t.right == null) {
			return 1;
		}
		return leaves(t.left) + leaves(t.right);
	}

	public int fullNodes() {
		return fullNodes(root);
	}

	/**
	 * Counts the number of full nodes in the tree. Running time is ϴ(n),
	 * because each full node is counted once.
	 * 
	 * @return the number of full nodes in tree.
	 */
	private int fullNodes(BinaryNode<AnyType> t) {
		if (t == null) {
			return 0;
		}
		if (t.left != null && t.right != null) {
			return 1 + fullNodes(t.left) + fullNodes(t.right);
		}
		return fullNodes(t.left) + fullNodes(t.right);
	}

	public void range(AnyType k1, AnyType k2) {
		if (k1.compareTo(k2) > 0) {
			AnyType temp = k1;
			k1 = k2;
			k2 = temp;
		} 
		range(k1, k2, root);
	}

	/**
	 * (Bound) the Running time is ϴ(log n)
	 */
	private void range(AnyType k1, AnyType k2, BinaryNode<AnyType> t) {
		if (t != null) {
			if (t.element.compareTo(k1) > 0) {
				range(k1, k2, t.left);
			}
			if (t.element.compareTo(k2) < 0) {
				range(k1, k2, t.right);
			}
			if (t.element.compareTo(k1) >= 0 && t.element.compareTo(k2) <= 0) {
				System.out.println(t.element);
			}
		}
	}

	private void levelOrder(){
		QueueLi<BinaryNode<AnyType>> temp = new QueueLi<BinaryNode<AnyType>>();
		BinaryNode<AnyType> t;
		if(root != null){
			temp.enqueue(root);
		}
		while(!temp.empty()){
			t = temp.dequeue();

			if(t.left != null){
				temp.enqueue(t.left);
			}
			if(t.right != null){
				temp.enqueue(t.right);
			}
			System.out.println(t.element);
		}
	}



	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return t; // Item not found; do nothing

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true; // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	/**
	 * Internal method to print a subtree in preorder.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTreePreOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			System.out.println(t.element);
			printTreePreOrder(t.left);
			printTreePreOrder(t.right);
		}
	}

	/**
	 * Internal method to print a subtree in postorder.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTreePostOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			printTreePostOrder(t.left);
			printTreePostOrder(t.right);
			System.out.println(t.element);
		}
	}

	/**
	 * Internal method to compute height of a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType> {
		// Constructors
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

		AnyType element; // The data in the node
		BinaryNode<AnyType> left; // Left child
		BinaryNode<AnyType> right; // Right child
	}

	/** The tree root. */

	private BinaryNode<AnyType> root;

	public AnyType find(AnyType x) {
		if (isEmpty()) {
			throw new UnderflowException();
		}
		BinaryNode<AnyType> resultNode = find(x, root);
		return resultNode == null ? null : resultNode.element;
	}

	private BinaryNode<AnyType> find(AnyType x, BinaryNode<AnyType> t) {
		while (t != null) {
			int compareResult = x.compareTo(t.element);
			if (compareResult == 0) {
				return t;
			} else if (compareResult < 0) {
				t = t.left;
			} else if (compareResult > 0) {
				t = t.right;
			}
		}
		return null;
	}

	// Test program
	public static void main(String[] args) {
		BinarySearchTree<Integer> test = new BinarySearchTree<>();
		test.insert(10);
		test.insert(12);
		test.insert(16);
		test.insert(14);
		test.insert(20);
		test.insert(15);
		test.insert(3);
		test.insert(9);
		test.insert(7);
		test.insert(6);
		System.out.println("Now Printing Tree");
		test.printTree();
		System.out.println("Now Printing Tree Post Order");
		test.printTreePostOrder();
		System.out.println("Now Printing Tree Pre Order");
		test.printTreePreOrder();
		System.out.println("\n Number of nodes is: " + test.nodes());
		System.out.println("\n Number of leaves is: " + test.leaves());
		System.out.println("\n Number of full nodes is: " + test.fullNodes());

		System.out.println("\n Numbers in range of 16 and 10 is: ");
		test.range(10, 16);
		System.out.println("\n Numbers in range with k1 > k2:");
		test.range(16, 10);

		System.out.println("\n Printing in print level order: ");
		test.levelOrder();

		BinarySearchTree<Integer> empty = new BinarySearchTree<>();
		System.out.println("\n Now Printing Empty Tree");
		empty.printTree();
		System.out.println("\n Number of nodes is: " + empty.nodes());
		System.out.println("\n Number of leaves is: " + empty.leaves());
		System.out.println("\n Number of full nodes is: " + empty.fullNodes());
		
		BinarySearchTree<String> levels = new BinarySearchTree<String>();
		levels.insert("-");
		levels.insert("*");
	    levels.insert(" *");
	    levels.insert("a");
	    levels.insert("b");
	    levels.insert("+");
	    levels.insert("c");
	    levels.insert("d");
	    levels.insert("e");
	    System.out.println("\n Printing 4.71 in level order:");
	    levels.levelOrder();
	    
	    BinarySearchTree<Integer> figure = new BinarySearchTree<>();
	    figure.insert(10);
	    figure.insert(4);
	    figure.insert(2);
	    figure.insert(1);
	    figure.insert(3);
	    figure.insert(6);
	    figure.insert(5);
	    figure.insert(8);
	    figure.insert(7);
	    figure.insert(9);
	    figure.insert(11);
	    figure.insert(12);
	    figure.insert(13);
	    System.out.println("\n Printing 4.72 in level order:");
	    figure.levelOrder();

	}
}