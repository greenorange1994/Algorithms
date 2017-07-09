import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		int num = Integer.parseInt(args[0]);
		
		while(!StdIn.isEmpty()) {
			s.enqueue(StdIn.readString());
		}
		
		for (int i = 0; i < num; i++) {
			StdOut.println(s.dequeue());
		}
	}
}
