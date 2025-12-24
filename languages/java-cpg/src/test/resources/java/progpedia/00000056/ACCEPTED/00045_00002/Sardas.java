import java.util.*;
import java.io.*;


class SNo{
	public LinkedList<SNo> adj;
    public boolean visited;         
    public double distance; 
    public float x,y;           

    SNo(float x,float y) {
		adj = new LinkedList<SNo>();
		visited = false;
		distance = -1;
		this.x = x;
		this.y = y;

	}
	double paraDist(SNo t){
		return Math.sqrt((x - t.x) * (x - t.x) + (y - t.y) * (y - t.y));
	}
}
class SGraph {
    int n;   
    SNo nodes[];
    LinkedList<SNo> q;    

    SGraph(int n) {

	this.n = n;
	nodes  = new SNo[n+1];

	}

	public void addNo(float x,float y, int i){
	    nodes[i] = new SNo(x,y);
	}

	public void addLink(){
		for(int i=1;i<=n;i++)
    		for(int j=1;j<=n;j++)
    			if(j!=i)
    				nodes[i].adj.add(nodes[j]);
    }

    public SNo nMin(){
    	double min = 100000.0;
    	SNo b = nodes[1];
    	for(SNo w : q)
    		if(w.distance < min){
    			min = w.distance;
    			b = w;
     		}

     	return b;
    }

    public void prim(int start){

    	q = new LinkedList<SNo>();

    	for(int i=1;i<=n;i++)
    		q.add(nodes[i]);

    	nodes[start].distance = 0.0;

    	while(q.size()>0){
    		SNo u = nMin();
    		q.remove(u);
    		for(SNo w : u.adj)
    			if(q.indexOf(w)!=-1 && u.paraDist(w) < w.distance)
    				w.distance = u.paraDist(w);

    	}
    	double sum = 0.0;
    	for(int i=1;i<=n;i++)
    		sum += nodes[i].distance;

    	System.out.printf("%.2f\n",sum);


    }


}
public class Sardas{

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		SGraph g = new SGraph(n);

		for(int i=1;i<=n;i++)
			g.addNo(input.nextFloat(),input.nextFloat(),i);

		g.addLink();

		for(int i=1;i<=n;i++)
			g.nodes[i].distance = 100000.0;

		g.prim(1);



	}
}