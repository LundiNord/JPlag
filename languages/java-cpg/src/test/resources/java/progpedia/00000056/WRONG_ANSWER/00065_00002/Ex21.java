import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Ex21 {

	static UndirectedGraph<Integer> primed;
	static boolean [] visited = new boolean [101];

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		double [][] list = new double [n+1][2];
		
		Integer [] nodes = new Integer[n+1];
		
		
		UndirectedGraph<Integer> graph = new Ex21().new UndirectedGraph<Integer>();
		
		
		
		for(int x = 0; x < n ; x++){
			list[x][0] = Double.parseDouble(in.next());
			list[x][1] = Double.parseDouble(in.next());
			nodes[x] = new Integer(x);
			graph.addNode(nodes[x]);
		}
		
		for(int x = 0; x < n ; x++){
			
			for(int y = x + 1; y < n ; y++){
				
				double length = Math.sqrt(Math.pow((list[y][0]-list[x][0]), 2) + Math.pow((list[y][1]-list[x][1]), 2));
				
				graph.addEdge(nodes[x], nodes[y], length);
				
			}
			
		}
		
		primed = Prim.mst(graph);
		
		
		//System.out.println(primed.size());
		//System.out.println(primed);
		
		visited[0] = true;
		Map<Integer, Double> map = primed.edgesFrom(nodes[0]);
		
		double value = minimal(map);
		
		System.out.printf("%.2f",value);
	}
	
	
	static double minimal(Map<Integer, Double> m){
		
		//System.out.println(m.toString());
		
		if(m.isEmpty()){
			return 0;
		}
		else{
			Set<Integer> keys = m.keySet();
			
			for(Integer key:keys){
				if(visited[key.intValue()] == false){
					double dou = m.get(key);
					visited[key.intValue()] = true;
					return dou + minimal(primed.edgesFrom(key));
				}
				
				
			}
			return 0;
		}
		
	}
	
	

	public final static class Prim {
	    /**
	     * Given a connected undirected graph with real-valued edge costs,
	     * returns an MST of that graph.
	     *
	     * @param graph The graph from which to compute an MST.
	     * @return A spanning tree of the graph with minimum total weight.
	     */
	    public static <T> UndirectedGraph<T> mst(UndirectedGraph<T> graph) {
	        /* The Fibonacci heap we'll use to select nodes efficiently. */
	        FibonacciHeap<T> pq = new FibonacciHeap<T>();

	        /* This Fibonacci heap hands back internal handles to the nodes it
	         * stores.  This map will associate each node with its entry in the
	         * Fibonacci heap.
	         */
	        Map<T, FibonacciHeap.Entry<T>> entries = new HashMap<T, FibonacciHeap.Entry<T>>();

	        /* The graph which will hold the resulting MST. */
	        UndirectedGraph<T> result = new Ex21().new UndirectedGraph<T>();

	        /* As an edge case, if the graph is empty, just hand back the empty
	         * graph.
	         */
	        if (graph.isEmpty())
	            return result;

	        /* Pick an arbitrary starting node. */
	        T startNode = graph.iterator().next();

	        /* Add it as a node in the graph.  During this process, we'll use
	         * whether a node is in the result graph or not as a sentinel of
	         * whether it's already been picked.
	         */
	        result.addNode(startNode);

	        /* Begin by adding all outgoing edges of this start node to the
	         * Fibonacci heap.
	         */
	        addOutgoingEdges(startNode, graph, pq, result, entries);

	        /* Now, until we have added |V| - 1 edges to the graph, continously
	         * pick a node and determine which edge to add.
	         */
	        for (int i = 0; i < graph.size() - 1; ++i) {
	            /* Grab the cheapest node we can add. */
	            T toAdd = pq.dequeueMin().getValue();

	            /* Determine which edge we should pick to add to the MST.  We'll
	             * do this by getting the endpoint of the edge leaving the current
	             * node that's of minimum cost and that enters the visited edges.
	             */
	            T endpoint = minCostEndpoint(toAdd, graph, result);

	            /* Add this edge to the graph. */
	            result.addNode(toAdd);
	            result.addEdge(toAdd, endpoint, graph.edgeCost(toAdd, endpoint));

	            /* Explore outward from this node. */
	            addOutgoingEdges(toAdd, graph, pq, result, entries);
	        }

	        /* Hand back the generated graph. */
	        return result;
	    }

	    /**
	     * Given a node in the source graph and a set of nodes that we've visited
	     * so far, returns the minimum-cost edge from that node to some node that
	     * has been visited before.
	     *
	     * @param node The node that has not been considered yet.
	     * @param graph The original graph whose MST is being computed.
	     * @param result The resulting graph, used to check what has been visited
	     *               so far.
	     */
	    private static <T> T minCostEndpoint(T node, UndirectedGraph<T> graph, 
	                                         UndirectedGraph<T> result) {
	        /* Track the best endpoint so far and its cost, initially null and
	         * +infinity.
	         */
	        T endpoint = null;
	        double leastCost = Double.POSITIVE_INFINITY;

	        /* Scan each node, checking whether it's a candidate. */
	        for (Map.Entry<T, Double> entry : graph.edgesFrom(node).entrySet()) {
	            /* If the endpoint isn't in the nodes constructed so far, don't
	             * consider it.
	             */
	            if (!result.containsNode(entry.getKey())) continue;

	            /* If the edge costs more than what we know, skip it. */
	            if (entry.getValue() >= leastCost) continue;

	            /* Otherwise, update our guess to be this node. */
	            endpoint = entry.getKey();
	            leastCost = entry.getValue();
	        }

	        /* Hand back the result.  We're guaranteed to have found something,
	         * since otherwise we couldn't have dequeued this node.
	         */
	        return endpoint;
	    }

	    /**
	     * Given a node in the graph, updates the priorities of adjacent nodes to
	     * take these edges into account.  Due to some optimizations we make, this
	     * step takes in several parameters beyond what might seem initially
	     * required.  They are explained in the param section below.
	     *
	     * @param node The node to explore outward from.
	     * @param graph The graph whose MST is being computed, used so we can
	     *              get the edges to consider.
	     * @param pq The Fibonacci heap holding each endpoint.
	     * @param result The result graph.  We need this information so that we
	     *               don't try to update information on a node that has
	     *               already been considered and thus isn't in the queue.
	     * @param entries A map from nodes to their corresponding heap entries.
	     *                We need this so we can call decreaseKey on the correct
	     *                elements.
	     */
	    private static <T> void addOutgoingEdges(T node, UndirectedGraph<T> graph,
	                                             FibonacciHeap<T> pq,
	                                             UndirectedGraph<T> result,
	                                             Map<T, FibonacciHeap.Entry<T>> entries ) {
	        /* Start off by scanning over all edges emanating from our node. */
	        for (Map.Entry<T, Double> arc : graph.edgesFrom(node).entrySet()) {
	            /* Given this arc, there are four possibilities.
	             *
	             * 1. This endpoint has already been added to the graph.  If so,
	             *    we ignore the edge since it would form a cycle.
	             * 2. This endpoint is not in the graph and has never been in
	             *    the heap.  Then we add it to the heap.
	             * 3. This endpoint is in the graph, but this is a better edge.
	             *    Then we use decreaseKey to update its priority.
	             * 4. This endpoint is in the graph, but there is a better edge
	             *    to it.  In that case, we similarly ignore it.
	             */
	            if (result.containsNode(arc.getKey())) continue; // Case 1

	            if (!entries.containsKey(arc.getKey())) { // Case 2
	                entries.put(arc.getKey(), pq.enqueue(arc.getKey(), arc.getValue()));
	            }
	            else if (entries.get(arc.getKey()).getPriority() > arc.getValue()) { // Case 3
	                pq.decreaseKey(entries.get(arc.getKey()), arc.getValue());
	            }

	            // Case 4 handled implicitly by doing nothing.
	        }
	    }
	}; 
	
	
	
	public final class UndirectedGraph<T> implements Iterable<T> {
	    /* A map from nodes in the graph to sets of outgoing edges.  Each
	     * set of edges is represented by a map from edges to doubles.
	     */
	    private final Map<T, Map<T, Double>> mGraph = new HashMap<T, Map<T, Double>>();

	    /**
	     * Adds a new node to the graph.  If the node already exists, this
	     * function is a no-op.
	     *
	     * @param node The node to add.
	     * @return Whether or not the node was added.
	     */
	    public boolean addNode(T node) {
	        /* If the node already exists, don't do anything. */
	        if (mGraph.containsKey(node))
	            return false;

	        /* Otherwise, add the node with an empty set of outgoing edges. */
	        mGraph.put(node, new HashMap<T, Double>());
	        return true;
	    }

	    /**
	     * Given two nodes and a length, adds an arc of that length between those
	     * nodes.  If the arc already existed, the length is updated to the
	     * specified value.  If either endpoint does not exist in the graph, throws
	     * a NoSuchElementException.
	     *
	     * @param one The first node.
	     * @param two The second node.
	     * @param length The length of the edge.
	     * @throws NoSuchElementException If either the start or destination nodes
	     *                                do not exist.
	     */
	    public void addEdge(T one, T two, double length) {
	        /* Confirm both endpoints exist. */
	        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
	            throw new NoSuchElementException("Both nodes must be in the graph.");

	        /* Add the edge in both directions. */
	        mGraph.get(one).put(two, length);
	        mGraph.get(two).put(one, length);
	    }

	    /**
	     * Removes the edge between the indicated endpoints from the graph.  If the
	     * edge does not exist, this operation is a no-op.  If either endpoint does
	     * not exist, this throws a NoSuchElementException.
	     *
	     * @param one The start node.
	     * @param two The destination node.
	     * @throws NoSuchElementException If either node is not in the graph.
	     */
	    public void removeEdge(T one, T two) {
	        /* Confirm both endpoints exist. */
	        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
	            throw new NoSuchElementException("Both nodes must be in the graph.");

	        /* Remove the edges from both adjacency lists. */
	        mGraph.get(one).remove(two);
	        mGraph.get(two).remove(one);
	    }

	    /**
	     * Given two endpoints, returns the cost of the edge between them.  If
	     * either endpoint does not exist in the graph, or if the edge is not
	     * contained in the graph, this throws a NoSuchElementException.
	     *
	     * @param one The first endpoint.
	     * @param two The second endpoint.
	     * @return The cost of the edge between the endpoints.
	     * @throws NoSuchElementException If the edge is not found or the endpoints
	     *                                are not nodes in the graph.
	     */
	    public double edgeCost(T one, T two) {
	        /* Confirm both endpoints exist. */
	        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
	            throw new NoSuchElementException("Both nodes must be in the graph.");     
	        
	        /* Look up the edge between the two. */
	        Double result = mGraph.get(one).get(two);

	        /* If there is no edge here, report an error. */
	        if (result == null)
	            throw new NoSuchElementException("Edge does not exist in the graph.");

	        /* Otherwise return the cost. */
	        return result;
	    }

	    /**
	     * Given a node in the graph, returns an immutable view of the edges
	     * leaving that node, as a map from endpoints to costs.
	     *
	     * @param node The node whose edges should be queried.
	     * @return An immutable view of the edges leaving that node.
	     * @throws NoSuchElementException If the node does not exist.
	     */
	    public Map<T, Double> edgesFrom(T node) {
	        /* Check that the node exists. */
	        Map<T, Double> arcs = mGraph.get(node);
	        if (arcs == null)
	            throw new NoSuchElementException("Source node does not exist.");

	        return Collections.unmodifiableMap(arcs);
	    }

	    /**
	     * Returns whether a given node is contained in the graph.
	     *
	     * @param The node to test for inclusion.
	     * @return Whether that node is contained in the graph.
	     */
	    public boolean containsNode(T node) {
	        return mGraph.containsKey(node);
	    }

	    /**
	     * Returns an iterator that can traverse the nodes in the graph.
	     *
	     * @return An iterator that traverses the nodes in the graph.
	     */
	    public Iterator<T> iterator() {
	        return mGraph.keySet().iterator();
	    }

	    /**
	     * Returns the number of nodes in the graph.
	     *
	     * @return The number of nodes in the graph.
	     */
	    public int size() {
	        return mGraph.size();
	    }

	    /**
	     * Returns whether the graph is empty.
	     *
	     * @return Whether the graph is empty.
	     */
	    public boolean isEmpty() {
	        return mGraph.isEmpty();
	    }
	}
	
	
	public final static class FibonacciHeap<T> {
	    /* In order for all of the Fibonacci heap operations to complete in O(1),
	     * clients need to have O(1) access to any element in the heap.  We make
	     * this work by having each insertion operation produce a handle to the
	     * node in the tree.  In actuality, this handle is the node itself, but
	     * we guard against external modification by marking the internal fields
	     * private.
	     */
	    public static final class Entry<T> {
	        private int     mDegree = 0;       // Number of children
	        private boolean mIsMarked = false; // Whether this node is marked

	        private Entry<T> mNext;   // Next and previous elements in the list
	        private Entry<T> mPrev;

	        private Entry<T> mParent; // Parent in the tree, if any.

	        private Entry<T> mChild;  // Child node, if any.

	        private T      mElem;     // Element being stored here
	        private double mPriority; // Its priority

	        /**
	         * Returns the element represented by this heap entry.
	         *
	         * @return The element represented by this heap entry.
	         */
	        public T getValue() {
	            return mElem;
	        }
	        /**
	         * Sets the element associated with this heap entry.
	         *
	         * @param value The element to associate with this heap entry.
	         */
	        public void setValue(T value) {
	            mElem = value;
	        }

	        /**
	         * Returns the priority of this element.
	         *
	         * @return The priority of this element.
	         */
	        public double getPriority() {
	            return mPriority;
	        }

	        /**
	         * Constructs a new Entry that holds the given element with the indicated 
	         * priority.
	         *
	         * @param elem The element stored in this node.
	         * @param priority The priority of this element.
	         */
	        private Entry(T elem, double priority) {
	            mNext = mPrev = this;
	            mElem = elem;
	            mPriority = priority;
	        }
	    }

	    /* Pointer to the minimum element in the heap. */
	    private Entry<T> mMin = null;

	    /* Cached size of the heap, so we don't have to recompute this explicitly. */
	    private int mSize = 0;

	    /**
	     * Inserts the specified element into the Fibonacci heap with the specified
	     * priority.  Its priority must be a valid double, so you cannot set the
	     * priority to NaN.
	     *
	     * @param value The value to insert.
	     * @param priority Its priority, which must be valid.
	     * @return An Entry representing that element in the tree.
	     */
	    public Entry<T> enqueue(T value, double priority) {
	        checkPriority(priority);

	        /* Create the entry object, which is a circularly-linked list of length
	         * one.
	         */
	        Entry<T> result = new Entry<T>(value, priority);

	        /* Merge this singleton list with the tree list. */
	        mMin = mergeLists(mMin, result);

	        /* Increase the size of the heap; we just added something. */
	        ++mSize;

	        /* Return the reference to the new element. */
	        return result;
	    }

	    /**
	     * Returns an Entry object corresponding to the minimum element of the
	     * Fibonacci heap, throwing a NoSuchElementException if the heap is
	     * empty.
	     *
	     * @return The smallest element of the heap.
	     * @throws NoSuchElementException If the heap is empty.
	     */
	    public Entry<T> min() {
	        if (isEmpty())
	            throw new NoSuchElementException("Heap is empty.");
	        return mMin;
	    }

	    /**
	     * Returns whether the heap is empty.
	     *
	     * @return Whether the heap is empty.
	     */
	    public boolean isEmpty() {
	        return mMin == null;
	    }

	    /**
	     * Returns the number of elements in the heap.
	     *
	     * @return The number of elements in the heap.
	     */
	    public int size() {
	        return mSize;
	    }

	    /**
	     * Given two Fibonacci heaps, returns a new Fibonacci heap that contains
	     * all of the elements of the two heaps.  Each of the input heaps is
	     * destructively modified by having all its elements removed.  You can
	     * continue to use those heaps, but be aware that they will be empty
	     * after this call completes.
	     *
	     * @param one The first Fibonacci heap to merge.
	     * @param two The second Fibonacci heap to merge.
	     * @return A new FibonacciHeap containing all of the elements of both
	     *         heaps.
	     */
	    public static <T> FibonacciHeap<T> merge(FibonacciHeap<T> one, FibonacciHeap<T> two) {
	        /* Create a new FibonacciHeap to hold the result. */
	        FibonacciHeap<T> result = new FibonacciHeap<T>();

	        /* Merge the two Fibonacci heap root lists together.  This helper function
	         * also computes the min of the two lists, so we can store the result in
	         * the mMin field of the new heap.
	         */
	        result.mMin = mergeLists(one.mMin, two.mMin);

	        /* The size of the new heap is the sum of the sizes of the input heaps. */
	        result.mSize = one.mSize + two.mSize;

	        /* Clear the old heaps. */
	        one.mSize = two.mSize = 0;
	        one.mMin  = null;
	        two.mMin  = null;

	        /* Return the newly-merged heap. */
	        return result;
	    }

	    /**
	     * Dequeues and returns the minimum element of the Fibonacci heap.  If the
	     * heap is empty, this throws a NoSuchElementException.
	     *
	     * @return The smallest element of the Fibonacci heap.
	     * @throws NoSuchElementException If the heap is empty.
	     */
	    public Entry<T> dequeueMin() {
	        /* Check for whether we're empty. */
	        if (isEmpty())
	            throw new NoSuchElementException("Heap is empty.");

	        /* Otherwise, we're about to lose an element, so decrement the number of
	         * entries in this heap.
	         */
	        --mSize;

	        /* Grab the minimum element so we know what to return. */
	        Entry<T> minElem = mMin;

	        /* Now, we need to get rid of this element from the list of roots.  There
	         * are two cases to consider.  First, if this is the only element in the
	         * list of roots, we set the list of roots to be null by clearing mMin.
	         * Otherwise, if it's not null, then we write the elements next to the
	         * min element around the min element to remove it, then arbitrarily
	         * reassign the min.
	         */
	        if (mMin.mNext == mMin) { // Case one
	            mMin = null;
	        }
	        else { // Case two
	            mMin.mPrev.mNext = mMin.mNext;
	            mMin.mNext.mPrev = mMin.mPrev;
	            mMin = mMin.mNext; // Arbitrary element of the root list.
	        }

	        /* Next, clear the parent fields of all of the min element's children,
	         * since they're about to become roots.  Because the elements are
	         * stored in a circular list, the traversal is a bit complex.
	         */
	        if (minElem.mChild != null) {
	            /* Keep track of the first visited node. */
	            Entry<?> curr = minElem.mChild;
	            do {
	                curr.mParent = null;

	                /* Walk to the next node, then stop if this is the node we
	                 * started at.
	                 */
	                curr = curr.mNext;
	            } while (curr != minElem.mChild);
	        }

	        /* Next, splice the children of the root node into the topmost list, 
	         * then set mMin to point somewhere in that list.
	         */
	        mMin = mergeLists(mMin, minElem.mChild);

	        /* If there are no entries left, we're done. */
	        if (mMin == null) return minElem;

	        /* Next, we need to coalsce all of the roots so that there is only one
	         * tree of each degree.  To track trees of each size, we allocate an
	         * ArrayList where the entry at position i is either null or the 
	         * unique tree of degree i.
	         */
	        List<Entry<T>> treeTable = new ArrayList<Entry<T>>();

	        /* We need to traverse the entire list, but since we're going to be
	         * messing around with it we have to be careful not to break our
	         * traversal order mid-stream.  One major challenge is how to detect
	         * whether we're visiting the same node twice.  To do this, we'll
	         * spent a bit of overhead adding all of the nodes to a list, and
	         * then will visit each element of this list in order.
	         */
	        List<Entry<T>> toVisit = new ArrayList<Entry<T>>();

	        /* To add everything, we'll iterate across the elements until we
	         * find the first element twice.  We check this by looping while the
	         * list is empty or while the current element isn't the first element
	         * of that list.
	         */
	        for (Entry<T> curr = mMin; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.mNext)
	            toVisit.add(curr);

	        /* Traverse this list and perform the appropriate unioning steps. */
	        for (Entry<T> curr: toVisit) {
	            /* Keep merging until a match arises. */
	            while (true) {
	                /* Ensure that the list is long enough to hold an element of this
	                 * degree.
	                 */
	                while (curr.mDegree >= treeTable.size())
	                    treeTable.add(null);

	                /* If nothing's here, we're can record that this tree has this size
	                 * and are done processing.
	                 */
	                if (treeTable.get(curr.mDegree) == null) {
	                    treeTable.set(curr.mDegree, curr);
	                    break;
	                }

	                /* Otherwise, merge with what's there. */
	                Entry<T> other = treeTable.get(curr.mDegree);
	                treeTable.set(curr.mDegree, null); // Clear the slot

	                /* Determine which of the two trees has the smaller root, storing
	                 * the two tree accordingly.
	                 */
	                Entry<T> min = (other.mPriority < curr.mPriority)? other : curr;
	                Entry<T> max = (other.mPriority < curr.mPriority)? curr  : other;

	                /* Break max out of the root list, then merge it into min's child
	                 * list.
	                 */
	                max.mNext.mPrev = max.mPrev;
	                max.mPrev.mNext = max.mNext;

	                /* Make it a singleton so that we can merge it. */
	                max.mNext = max.mPrev = max;
	                min.mChild = mergeLists(min.mChild, max);
	                
	                /* Reparent max appropriately. */
	                max.mParent = min;

	                /* Clear max's mark, since it can now lose another child. */
	                max.mIsMarked = false;

	                /* Increase min's degree; it now has another child. */
	                ++min.mDegree;

	                /* Continue merging this tree. */
	                curr = min;
	            }

	            /* Update the global min based on this node.  Note that we compare
	             * for <= instead of < here.  That's because if we just did a
	             * reparent operation that merged two different trees of equal
	             * priority, we need to make sure that the min pointer points to
	             * the root-level one.
	             */
	            if (curr.mPriority <= mMin.mPriority) mMin = curr;
	        }
	        return minElem;
	    }

	    /**
	     * Decreases the key of the specified element to the new priority.  If the
	     * new priority is greater than the old priority, this function throws an
	     * IllegalArgumentException.  The new priority must be a finite double,
	     * so you cannot set the priority to be NaN, or +/- infinity.  Doing
	     * so also throws an IllegalArgumentException.
	     *
	     * It is assumed that the entry belongs in this heap.  For efficiency
	     * reasons, this is not checked at runtime.
	     *
	     * @param entry The element whose priority should be decreased.
	     * @param newPriority The new priority to associate with this entry.
	     * @throws IllegalArgumentException If the new priority exceeds the old
	     *         priority, or if the argument is not a finite double.
	     */
	    public void decreaseKey(Entry<T> entry, double newPriority) {
	        checkPriority(newPriority);
	        if (newPriority > entry.mPriority)
	            throw new IllegalArgumentException("New priority exceeds old.");

	        /* Forward this to a helper function. */
	        decreaseKeyUnchecked(entry, newPriority);
	    }
	    
	    /**
	     * Deletes this Entry from the Fibonacci heap that contains it.
	     *
	     * It is assumed that the entry belongs in this heap.  For efficiency
	     * reasons, this is not checked at runtime.
	     *
	     * @param entry The entry to delete.
	     */
	    public void delete(Entry<T> entry) {
	        /* Use decreaseKey to drop the entry's key to -infinity.  This will
	         * guarantee that the node is cut and set to the global minimum.
	         */
	        decreaseKeyUnchecked(entry, Double.NEGATIVE_INFINITY);

	        /* Call dequeueMin to remove it. */
	        dequeueMin();
	    }

	    /**
	     * Utility function which, given a user-specified priority, checks whether
	     * it's a valid double and throws an IllegalArgumentException otherwise.
	     *
	     * @param priority The user's specified priority.
	     * @throws IllegalArgumentException If it is not valid.
	     */
	    private void checkPriority(double priority) {
	        if (Double.isNaN(priority))
	            throw new IllegalArgumentException(priority + " is invalid.");
	    }

	    /**
	     * Utility function which, given two pointers into disjoint circularly-
	     * linked lists, merges the two lists together into one circularly-linked
	     * list in O(1) time.  Because the lists may be empty, the return value
	     * is the only pointer that's guaranteed to be to an element of the
	     * resulting list.
	     *
	     * This function assumes that one and two are the minimum elements of the
	     * lists they are in, and returns a pointer to whichever is smaller.  If
	     * this condition does not hold, the return value is some arbitrary pointer
	     * into the doubly-linked list.
	     *
	     * @param one A pointer into one of the two linked lists.
	     * @param two A pointer into the other of the two linked lists.
	     * @return A pointer to the smallest element of the resulting list.
	     */
	    private static <T> Entry<T> mergeLists(Entry<T> one, Entry<T> two) {
	        /* There are four cases depending on whether the lists are null or not.
	         * We consider each separately.
	         */
	        if (one == null && two == null) { // Both null, resulting list is null.
	            return null;
	        }
	        else if (one != null && two == null) { // Two is null, result is one.
	            return one;
	        }
	        else if (one == null && two != null) { // One is null, result is two.
	            return two;
	        }
	        else { // Both non-null; actually do the splice.
	            /* This is actually not as easy as it seems.  The idea is that we'll
	             * have two lists that look like this:
	             *
	             * +----+     +----+     +----+
	             * |    |--N->|one |--N->|    |
	             * |    |<-P--|    |<-P--|    |
	             * +----+     +----+     +----+
	             *
	             *
	             * +----+     +----+     +----+
	             * |    |--N->|two |--N->|    |
	             * |    |<-P--|    |<-P--|    |
	             * +----+     +----+     +----+
	             *
	             * And we want to relink everything to get
	             *
	             * +----+     +----+     +----+---+
	             * |    |--N->|one |     |    |   |
	             * |    |<-P--|    |     |    |<+ |
	             * +----+     +----+<-\  +----+ | |
	             *                  \  P        | |
	             *                   N  \       N |
	             * +----+     +----+  \->+----+ | |
	             * |    |--N->|two |     |    | | |
	             * |    |<-P--|    |     |    | | P
	             * +----+     +----+     +----+ | |
	             *              ^ |             | |
	             *              | +-------------+ |
	             *              +-----------------+
	             *
	             */
	            Entry<T> oneNext = one.mNext; // Cache this since we're about to overwrite it.
	            one.mNext = two.mNext;
	            one.mNext.mPrev = one;
	            two.mNext = oneNext;
	            two.mNext.mPrev = two;

	            /* Return a pointer to whichever's smaller. */
	            return one.mPriority < two.mPriority? one : two;
	        }
	    }

	    /**
	     * Decreases the key of a node in the tree without doing any checking to ensure
	     * that the new priority is valid.
	     *
	     * @param entry The node whose key should be decreased.
	     * @param priority The node's new priority.
	     */
	    private void decreaseKeyUnchecked(Entry<T> entry, double priority) {
	        /* First, change the node's priority. */
	        entry.mPriority = priority;

	        /* If the node no longer has a higher priority than its parent, cut it.
	         * Note that this also means that if we try to run a delete operation
	         * that decreases the key to -infinity, it's guaranteed to cut the node
	         * from its parent.
	         */
	        if (entry.mParent != null && entry.mPriority <= entry.mParent.mPriority)
	            cutNode(entry);

	        /* If our new value is the new min, mark it as such.  Note that if we
	         * ended up decreasing the key in a way that ties the current minimum
	         * priority, this will change the min accordingly.
	         */
	        if (entry.mPriority <= mMin.mPriority)
	            mMin = entry;
	    }

	    /**
	     * Cuts a node from its parent.  If the parent was already marked, recursively
	     * cuts that node from its parent as well.
	     *
	     * @param entry The node to cut from its parent.
	     */
	    private void cutNode(Entry<T> entry) {
	        /* Begin by clearing the node's mark, since we just cut it. */
	        entry.mIsMarked = false;

	        /* Base case: If the node has no parent, we're done. */
	        if (entry.mParent == null) return;

	        /* Rewire the node's siblings around it, if it has any siblings. */
	        if (entry.mNext != entry) { // Has siblings
	            entry.mNext.mPrev = entry.mPrev;
	            entry.mPrev.mNext = entry.mNext;
	        }

	        /* If the node is the one identified by its parent as its child,
	         * we need to rewrite that pointer to point to some arbitrary other
	         * child.
	         */
	        if (entry.mParent.mChild == entry) {
	            /* If there are any other children, pick one of them arbitrarily. */
	            if (entry.mNext != entry) {
	                entry.mParent.mChild = entry.mNext;
	            }
	            /* Otherwise, there aren't any children left and we should clear the
	             * pointer and drop the node's degree.
	             */
	            else {
	                entry.mParent.mChild = null;
	            }
	        }

	        /* Decrease the degree of the parent, since it just lost a child. */
	        --entry.mParent.mDegree;

	        /* Splice this tree into the root list by converting it to a singleton
	         * and invoking the merge subroutine.
	         */
	        entry.mPrev = entry.mNext = entry;
	        mMin = mergeLists(mMin, entry);

	        /* Mark the parent and recursively cut it if it's already been
	         * marked.
	         */
	        if (entry.mParent.mIsMarked)
	            cutNode(entry.mParent);
	        else
	            entry.mParent.mIsMarked = true;

	        /* Clear the relocated node's parent; it's now a root. */
	        entry.mParent = null;
	    }
	}
	
}



