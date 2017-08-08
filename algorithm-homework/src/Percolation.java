import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	// percolationGrid is a 2D array with each site either zero(blocked) or one(unblocked)
	private int[][] percolationGrid;
	
	// percolationArray maintains the data we need for union find. It's length is equal to n squared.
	private int[]percolationArray;

	// create an n x n grid with all sites blocked
	public Percolation(int n) {
		this.percolationGrid = new int[n+1][n+1];
		this.percolationArray = new int[n^2];
	}
	
	// open site if it is not open already
	public void open(int row, int col) {
		
	}
	
	// is site open?
	public boolean isOpen(int row, int col) {
		
	}
	
	// is site full?
	public boolean isFull(int row, int col) {
		
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		
	}
	
	// does the system percolate?
	public boolean percolates() {
		
	}
	
	// optional test client
	public static void main(String[] args) {
		
	}
	
	
	// private methods 
	
	// gridToArray translates row and col into an int that corresponds to a site in percolationArray
	private int gridToArray(int row,int col) {
		return ((row-1) * 10) + (col - 1);
	}
	
	// array to grid translates an array number to an x,y location
	private int[] arrayToGrid(int site) {
		int row = site / 10 +1;
		int col = site % 10 +1;
		int[] result = {row,col};
		return result;
	}
}
