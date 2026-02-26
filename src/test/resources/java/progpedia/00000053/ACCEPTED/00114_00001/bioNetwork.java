import java.util.*;
public class bioNetwork
{
	static int distancesArray[][];	// Global array of distances between knots.
	static int nodes;				// Number of knots.
	// A list of linked lists:
	// Each element of the ArrayList represents a knot
	// Each element of the LinkedList represent the connected knots a knot has.
	static ArrayList<LinkedList<Integer>> nodeMap;
	static boolean checkArray[];	// Checks wich knots are left to visit.
	public static void main (String args[])
	{
		Scanner input = new Scanner(System.in);	// Scanner declaration
		nodes = input.nextInt();	// Number of nodes.
		int links = input.nextInt();	// Number of links.
		distancesArray = new int[nodes][nodes];	// Giving size to the distances array.
		nodeMap = new ArrayList<LinkedList<Integer>>();	// Creating the array of lists.
        for(int i=0;i<nodes;i++)	// Adding linked lists to the array.
        	nodeMap.add(new LinkedList<Integer>());
		for(int i=0;i<links;i++)	
		{
			int a = input.nextInt();	// Reading first knot.
			int b = input.nextInt();	// Reading second knot.
			nodeMap.get(a-1).add(b-1);	// Finding first knot in the array and adding second knot to its linked list.
			nodeMap.get(b-1).add(a-1);
		}
		checkArray = new boolean[nodes];	// Defining checkArray, it will tell for each knot wich knots are left to visit.
		for (int i=0;i<nodes;i++)			// For each node, all other nodes are to visit.
		{
            Arrays.fill(checkArray,false);	// All nodes are not visited.
            bfs(i);							// Width search.
    }
    //Building an array of excentricity
    int excentricity = 0;	// Starts at 0.
    int exArray[] = new int[nodes];
    for(int i=0;i<nodes;i++)
      {
	       for(int i2=0;i2<nodes;i2++)
	       {
	        // It finds the excentricity of each node.
	        if(distancesArray[i][i2] > excentricity)
	        {
	        	excentricity = distancesArray[i][i2];
	        }   	
	       }	
	       exArray[i] = excentricity;	// After the excentricity is found, it is stored in the array.
	       excentricity = 0;			// And after that it goes back to zero.
        }
        // Getting width and radius.
        int width = Integer.MIN_VALUE;	// Is the biggest excentricity of all nodes.
        int radius = Integer.MAX_VALUE; // Is the lowest excentricity of all nodes.
        for(int i=0;i<nodes;i++)
        {
	        if(exArray[i] > width) width = exArray[i];
	        if(exArray[i] < radius) radius = exArray[i];
        }
       	// Getting centrals and periferic
       	boolean isCentral[] = new boolean[nodes];	// Creating isCentral bool array.
       	boolean isPeri[] = new boolean[nodes];	// Creating isPeriferic bool array.
       	Arrays.fill(isCentral,false);				// Fills with false
       	Arrays.fill(isPeri,false);				// Fills with false
       	// If the excentricit of a node is equal to the radius, then that node is central, thus it says true in the array.
       	for(int i=0;i<nodes;i++)
       	{
       		if(exArray[i] == radius) isCentral[i] = true;
       		if(exArray[i] == width) isPeri[i] = true; //If the excentricity equals the width then it is periferic.
       	}
       	//OUTPUT
      	System.out.println(width);
      	System.out.println(radius);
        int helper = 0;
      	for(int i=0;i<nodes;i++)
      	{
      		if(isCentral[i]==true)
      		{
      			if(helper==0)System.out.print(i+1);
            else System.out.print(" " + (i+1));
            helper++;
      		}
      	}
		    System.out.println();
        helper = 0;
      	for(int i=0;i<nodes;i++)
      	{
      		if(isPeri[i]==true)
      		{
      			if(helper==0)System.out.print(i+1);
            else System.out.print(" " + (i+1));
            helper++;
      		}
      	}
      	System.out.println();
	}
	static void bfs (int node) {
        LinkedList<Integer> nodeQueue = new LinkedList<Integer>();
        nodeQueue.add(node);
        distancesArray[node][node] = 0;
        checkArray[node] = true;
        while (!nodeQueue.isEmpty()) {
            int currentNode = nodeQueue.remove();
            for(int linkedTo : nodeMap.get(currentNode)) {
                if (!checkArray[linkedTo]) {
                    nodeQueue.add(linkedTo);
                    checkArray[linkedTo] = true;
                    distancesArray[node][linkedTo] = distancesArray[node][currentNode] + 1;
                }
            }
        }
    }
	
}