/** Matt West 8/9/17 - Percolation challenge - (insert run instructions here)
 * 
 */

// idea: think about using more than one than one grid. Possibly one more for checking percolation. That would allow virtual site at top.
// to avoid 'backwash' use only one virtual site and create a 'percolates' field you can set to true.

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	// grid is a 2D array with each site either zero(blocked) or one(open)
	// Percolation application doesn't allow grid row 0 or column 0 as "real" sites.
	// Row 0 holds two virtual sites at 0,0 and 0,1
	// grid size is n+1 x n+1 to compensate for unused row 0 and col 0.
	private int[][] grid;

	// connections tracks union find connected components
	private WeightedQuickUnionUF connections;

	// fullSites tracks whether or not a grid site is full.
	private WeightedQuickUnionUF fullSites;

	// openSites maintains a count of how many grid sites are open
	private int openSites;

	// n is the number of sites we're working with
	private int N;

	// size is length of the connections array
	private int size = N ^ 2 + 2;

	// constructor
	public Percolation(int n) {
		// validate n
		if (N <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		// initialize all fields
		this.N = n;
		// size is (N^2+2) to accomodate virutal sites.
		this.size = n ^ 2 + 2;
		// create array + 2 extra id locations for virtual sites. 0 and
		// connections.length -1 will be those.
		this.connections = new WeightedQuickUnionUF(size);
		// create array + 1 extra id for fullSites. Don't want two to prevent backwash
		// problem. 0 will be virutal site.
		this.fullSites = new WeightedQuickUnionUF(size - 1);
		// connect top virtual sites
		for (int i = 1; i <= N; i++) {
			// connect 0 index to top row
			connections.union(0, i);
			// connect last element to bottom row
			connections.union(size -1, (size -1) -i);
			// connect the one virtual site in fullSites to the top row for percolation
			fullSites.union(0, i);
		}

	}

	// open site if it is not open already
	public void open(int row, int col) {
		// validate input
		validateIndicies(row, col);

		// Mark site as open. If a site in the grid is closed it has a 0 value. If open,
		// 1.
		
		}
		// check neighbor sites. If they are open, union to them.
		// find percolationArray position based on row and col

		int arrayPos = gridToArray(row, col);
		// top neighbor
		int top = arrayPos - grid.length;
		// bottom neighbor
		int bottom = arrayPos + grid.length;
		// left neightbor
		int left = arrayPos - 1;
		// right neighbor
		int right = arrayPos + 1;

		int[] neighbors = { top, bottom, left, right };

		

	// is site open?
	public boolean isOpen(int row, int col) {
		return grid[row][col] > 0;
	}

	// is site full?
	public boolean isFull(int row, int col) {
		return grid[row][col] == 2;
	}

	// number of open sites
	public int numberOfOpenSites() {
		return openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		return fullSites[0][1] == 1;
	}

	// optional test client
	public static void main(String[] args) {

	}

	// private methods

	// gridToArray translates row and col into an int that corresponds to a site in
	// percolationArray
	private int gridToArray(int row, int col) {
		return ((row) * 10) + (col);
	}

	// array to grid translates an array number to an x,y location
	private int[] arrayToGrid(int site) {
		int row = site / 10;
		int col = site % 10;
		int[] result = { row, col };
		return result;
	}

	// validate idicies. No zeros.
	private void validateIndicies(int row, int col) {
		if (row <= 0 || row > n) {
			throw new IndexOutOfBoundsException("row index " + row + " out of bounds");
		}

		if (col <= 0 || col > n) {
			throw new IndexOutOfBoundsException("column index " + col + " out of bounds");
		}
	}
}
