import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int n;
	private Item[] a;
	
	public RandomizedQueue() {				// construct an empty randomized queue
		n = 0;
		a = (Item[]) new Object[2];
	}
	public boolean isEmpty() {				// is the queue empty?
		return n == 0;
	}
	public int size() {						// return the number of items on the queue
		return n;
	}
	private void nullItem(Item item) {
		if (item == null) throw new IllegalArgumentException();
	}
	private void resize(int capacity) {
		assert capacity >= n;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			temp[i] = a[i];
		}
		a = temp;
    }
	public void enqueue(Item item) {		// add the item
		nullItem(item);
		if (n == a.length) resize(2 * a.length);
		a[n++] = item;
	}
	public Item dequeue() {					// remove and return a random item
		if(isEmpty()) throw new NoSuchElementException();
		int idx = StdRandom.uniform(0, n);
		Item rm_item = a[idx];
		if (idx != n-1) a[idx] = a[n-1];
		a[n-1] = null;
		--n;
		if(n > 0 && n == a.length / 4) resize(a.length / 2);
		return rm_item;
	}
	public Item sample() {					// return (but do not remove) a random item
		if(isEmpty()) throw new NoSuchElementException();
		int idx = StdRandom.uniform(0, n);
		return a[idx];
	}
	public Iterator<Item> iterator() {		// return an independent iterator over items in random order
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item> {
		private Item[] itr_a;
		private int idx;

		public ArrayIterator() {
			itr_a = (Item[]) new Object[n];
			for(int i = 0; i < n; i++) {
				itr_a[i] = a[i];
			} 
			/* the initialization of an array is implemented element by element, 
			 you cannot use itr_a = a to assign all a values to itr_a */
			StdRandom.shuffle(itr_a);
			idx = 0;
		}

		public boolean hasNext()  { return idx < n;                   }
		public void remove()      { throw new UnsupportedOperationException();  }
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return itr_a[idx++];
		}
	}
	public static void main(String[] args) { // unit testing (optional)
		
	}
}
