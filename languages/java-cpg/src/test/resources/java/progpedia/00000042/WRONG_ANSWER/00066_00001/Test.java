import java.util.*;

class Segm {
	int b, e;

	public Segm(int b, int e) {
		this.b = b;
		this.e = e; }

	public static void quicksort(int[] main, int[] index) { quicksort(main, index, 0, index.length - 1); }

	// quicksort a[left] to a[right]
	public static void quicksort(int[] a, int[] index, int left, int right) {
	    if (right <= left) return;
	    int i = partition(a, index, left, right);
	    quicksort(a, index, left, i-1);
	    quicksort(a, index, i+1, right); }

	// partition a[left] to a[right], assumes left < right
	private static int partition(int[] a, int[] index, 
	int left, int right) {
	    int i = left - 1;
	    int j = right;
	    while (true) {
	        while (less(a[++i], a[right]))      // find item on left to swap
	            ;                               // a[right] acts as sentinel
	        while (less(a[right], a[--j]))      // find item on right to swap
	            if (j == left) break;           // don't go out-of-bounds
	        if (i >= j) break;                  // check if pointers cross
	        exch(a, index, i, j);               // swap two elements into place
	    }
	    exch(a, index, i, right);               // swap with partition element
	    return i; }

	// is x < y ?
	private static boolean less(int x, int y) { return (x < y); }

	// exchange a[i] and a[j]
	private static void exch(int[] a, int[] index, int i, int j) {
	    int swap = a[i];
	    a[i] = a[j];
	    a[j] = swap;
	    int b = index[i];
	    index[i] = index[j];
	    index[j] = b; }}

public class Test {
	public static void main (String[] args) {
	// Enter Code
	@SuppressWarnings("resource")
	Scanner in = new Scanner(System.in);

	int m = in.nextInt();
	int n = in.nextInt();

	int[] v1 = new int[n];
	int[] v2 = new int[n];
	Segm[] v3 = new Segm[n];

	for (int i=0; i<n; i++) {
		v1[i] = in.nextInt();
		v2[i] = i;
		v3[i] = new Segm(v1[i], in.nextInt()); }

	Segm.quicksort(v1, v2);
	int c = 0;//LinkedList<Segm> r = new LinkedList<Segm>();

	for (int i=0; i<n; i++) {
		int index = v2[i];

		LinkedList<Segm> ls = new LinkedList<Segm>();

		int b = v3[index].b;

		for (int j=i+1; j<n; j++) {
			if (v3[j].b != b) { i = j-1; break; }
			else ls.addLast(v3[j]); }

		int e = Integer.MIN_VALUE;

		while (!ls.isEmpty()) {
			int els = ls.removeFirst().e;
			if (els > e) e = els; }

		c++;
		if (e >= m) break; }

	System.out.println(c);

	/* MAIS CENAS */ }}