import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double mean_success;
	private double stddev_success;
	private double confidenceLo;
	private double confidenceHigh;

	public PercolationStats(int n, int trials) {	// perform trials independent experiments on an n-by-n grid
		if (trials <= 0 || n <= 0) {
			throw new IllegalArgumentException("index should be larger than zero");
		}
		double[] success = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation grid = new Percolation(n);
			int row;
			int col;
			int count = 0;
			while (!grid.percolates()) {
				do {
					row = StdRandom.uniform(1, n + 1);
					col = StdRandom.uniform(1, n + 1);					
				} while(grid.isOpen(row, col));
				grid.open(row, col);
				count++;
			}
			success[i] = (double)count / (double)(n * n);
		}
		mean_success = StdStats.mean(success);
		stddev_success = StdStats.stddev(success);
		double interval = 1.96 * stddev_success / Math.sqrt((double)trials);
		confidenceLo = mean_success - interval ;
		confidenceHigh = mean_success + interval;
	}
	   
	public double mean()                       {	// sample mean of percolation threshold
		return mean_success;
	}
	   
	public double stddev()                     {	// sample standard deviation of percolation threshold
		return stddev_success;
	}
	   
	public double confidenceLo()               {	// low  endpoint of 95% confidence interval
 		return confidenceLo;
	}
	   
	public double confidenceHi()               {	// high endpoint of 95% confidence interval
		return confidenceHigh;
	}

	public static void main(String[] args) {		// test client (described below)
	}
}
