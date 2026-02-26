import java.util.*;

class Segm {
	int b, e, s;

	public Segm(int b, int e) {
		this.b = b;
		this.e = e;
		this.s = e-b; }

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

	//for (int i=0; i<n; i++) System.out.println(v3[v2[i]].b + " " + v3[v2[i]].e);

	LinkedList<Segm> r = new LinkedList<Segm>();
	for (int i=0; i<n; i++) {
		LinkedList<Segm> ls = new LinkedList<Segm>();

		for (int j=i; j<n; j++) {
			if (v3[v2[j]].b > v3[v2[i]].b) { i=j-1; break; } 
			else if (v3[v2[j]].b == v3[v2[i]].b) ls.addLast(v3[v2[j]]); }

		Segm c = new Segm(Integer.MIN_VALUE, Integer.MIN_VALUE);

		while (!ls.isEmpty()) {
			Segm cls = ls.removeFirst();
			if (cls.e >= c.e && cls.s >= c.s) c = cls; }

		if (r.size() == 0) r.addLast(c);
		else { if (c.e > r.getLast().e) r.addLast(c); }

		if (c.e >= m) break; }

		System.out.println(r.size());

	/* MAIS CENAS */ }}