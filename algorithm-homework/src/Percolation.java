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
	private int size;

	// constructor
	public Percolation(int n) {
		// validate n
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		// initialize all fields
		this.N = n;
		int nSquared = N * N;
		// grid sites default to zero
		grid = new int[N][N];
		// size is (N^2+2) to accomodate virutal sites.
		this.size = nSquared + 2;
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
			connections.union(size - 1, (nSquared +1) - i);
			// connect the one virtual site in fullSites to the top row. This is our
			// 'source' for filling sites. Any open site connected to this site is full.
			fullSites.union(0, i);
		}

	}

	// open site if it is not open already
	public void open(int row, int col) {
		// validate input
		validateIndicies(row, col);
		// Mark site as open. If a site in the grid is closed it has a 0 value. If open,
		// 1
		grid[row-1][col-1] = 1;
		// increment openSites
		openSites++;
		// get index conversion for site
		int index = gridToIndex(row, col);

		// check neighbors for openness. If open, join them. Also join in fullSites
		// check top neighbor
		if (row > 1 && isOpen(row - 1, col)) {
			connections.union(index, gridToIndex(row - 1, col));
			fullSites.union(index, gridToIndex(row - 1, col));
		}
		// check right neighbor
		if (col < N && isOpen(row, col + 1)) {
			connections.union(index, gridToIndex(row, col + 1));
			fullSites.union(index, gridToIndex(row, col + 1));
		}
		// check bottom neighbor
		if (row < N && isOpen(row + 1, col)) {
			connections.union(index, gridToIndex(row + 1, col));
			fullSites.union(index, gridToIndex(row + 1, col));
		}
		// check left neighbor
		if (col > 1 && isOpen(row, col - 1)) {
			connections.union(index, gridToIndex(row, col - 1));
			fullSites.union(index, gridToIndex(row, col - 1));
		}

	}

	// is site open?
	public boolean isOpen(int row, int col) {
		validateIndicies(row, col);
		return grid[row -1][col -1] == 1;
	}

	// is site full?
	public boolean isFull(int row, int col) {
		validateIndicies(row, col);
		return isOpen(row,col) && fullSites.connected(gridToIndex(row, col), 0);

	}

	// number of open sites
	public int numberOfOpenSites() {
		return openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		if (N == 1) {
			return isOpen(1, 1);
		}
		// this is where we use the virtual sites
		return connections.connected(0, size - 1);
	}

	// optional test client
	public static void main(String[] args) {
//		Percolation perc = new Percolation(10);
//		perc.open(1, 1);
//		perc.open(2, 1);
//		System.out.println(perc.connections.connected(perc.gridToIndex(1, 1), perc.gridToIndex(1, 4)));
//
//		System.out.println(perc.connections.connected(perc.gridToIndex(1, 7), perc.gridToIndex(2, 1)));
		

	}

	// gridToArray translates row and col into an index number
	private int gridToIndex(int row, int col) {
		validateIndicies(row, col);
		return ((row - 1) * N + col);
	}

	// validate idicies. No zeros.
	private void validateIndicies(int row, int col) {
		if (row <= 0 || row > N) {
			throw new IndexOutOfBoundsException("row index " + row + " out of bounds");
		}

		if (col <= 0 || col > N) {
			throw new IndexOutOfBoundsException("column index " + col + " out of bounds");
		}
	}
}
