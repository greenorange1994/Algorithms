import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int shape;
	private int bottom;
	private boolean[] status;
	private WeightedQuickUnionUF site;
	private WeightedQuickUnionUF auxsite;
   
	public Percolation(int n) {					 // create n-by-n grid, with all sites blocked
		if (n <= 0) {
			throw new IllegalArgumentException("index" + n + "should be larger than zero");
		}
		shape = n;
		bottom = n * n + 1;
		site = new WeightedQuickUnionUF(bottom + 1);
		auxsite = new WeightedQuickUnionUF(bottom);
		status = new boolean[bottom+1];
		for (int i = 1; i < bottom; i++) {
			status[i] = false;
		}
		status[0] = true;
		status[bottom] = true;
	}
   
	public    void open(int row, int col)  {	// open site (row, col) if it is not open already
		int index = xyTo1D(row, col);
		if (!isOpen(row, col)) {
			status[index] = true;
			if (row != shape && isOpen(row + 1, col)) {
				site.union(index, xyTo1D(row + 1, col));
				auxsite.union(index, xyTo1D(row + 1, col));
			} 
			if (row == shape) {
				site.union(bottom, index);
			}
			if (row != 1 && isOpen(row - 1, col)) {
				site.union(index, xyTo1D(row - 1, col));
				auxsite.union(index, xyTo1D(row - 1, col));
			} 
			if (row == 1) {
				site.union(0, index);
				auxsite.union(0, index);
			}
			if (col != 1 && isOpen(row, col - 1)) {
				site.union(index, xyTo1D(row, col - 1));
				auxsite.union(index, xyTo1D(row, col - 1));
			}
			if (col != shape && isOpen(row, col + 1)) {
				site.union(index, xyTo1D(row, col + 1));
				auxsite.union(index, xyTo1D(row, col + 1));
			}
		}
	}
   
	public boolean isOpen(int row, int col) { // is site (row, col) open?
		int index = xyTo1D(row, col);
		return status[index];
	}
   
	public boolean isFull(int row, int col) { // is site (row, col) full?
		int idx = xyTo1D(row, col);
		return site.connected(0, idx) && auxsite.connected(0, idx);
	}
   
	public     int numberOfOpenSites()     { // number of open sites
		int count = 0;
		for (int i = 1; i < bottom; i++) {
			if (status[i]) {
				count = count + 1;
			}
		}
		return count;
	}
   
	public boolean percolates()            { // does the system percolate?
		return site.connected(0, bottom);
	}
   
	private void validate(int p) {
		if (p < 1 || p > shape) {
			throw new IllegalArgumentException("index" + p + "out of bounds");
		}
	}

	private int xyTo1D(int row, int col) {
		validate(row);
		validate(col);
		return (row - 1) * shape + col;
	}

	public static void main(String[] args) { // test client (optional)
	}
}
