import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private int n;
	private Node<Item> first;
	private Node<Item> last;
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> pre;
	}
	public Deque() {						// construct an empty deque
		first = null;
		last = null;
		n = 0;
	}
	public boolean isEmpty() {				// is the deque empty?
		return first == null;
	}
	public int size() {						// return the number of items on the deque
	   return n;
	}
	private void nullItem(Item item) {
		if (item == null) throw new IllegalArgumentException();
	}
	public void addFirst(Item item) {		// add the item to the front
		nullItem(item);
		Node<Item> oldfirst = first;
		first = new Node<Item>();			// replace null node with a new node
		first.pre = null;
		first.item = item;
		first.next = oldfirst;
		if (n == 0) last = first;
		else oldfirst.pre = first;
		n++;
	}
	public void addLast(Item item) {		// add the item to the end
		nullItem(item);
		Node<Item> oldlast = last;
		last = new Node<Item>();			// replace null node with a new node
		last.pre = oldlast;
		last.item = item;
		last.next = null;
		if(n == 0) first = last;
		else oldlast.next = last;
		n++;
	}
	public Item removeFirst() {				// remove and return the item from the front
		if (isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		--n;
		if(n == 0) last = first;
		else first.pre = null;
		return item;
	}
	public Item removeLast() {				// remove and return the item from the end
		if (isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		last = last.pre;
		--n;
		if(n == 0) first = null;
		else last.next = null;
		return item;
	}
	public Iterator<Item> iterator() {		// return an iterator over items in order from front to end
		return new ListIterator(first); 
	}
	private class ListIterator implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	public static void main(String[] args) { // unit testing (optional)
		Deque<Integer> dq = new Deque<Integer>();
		for (int i = 1; i < 11; i++) {
			dq.addFirst(i);
		}
		for (int element: dq) {
			StdOut.println(element);
		}
	}
}
