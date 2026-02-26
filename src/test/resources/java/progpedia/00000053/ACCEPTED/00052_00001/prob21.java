import java.util.*;
import java.io.*;

class prob21{
    public static void main(String args[]){
	
	int aux1, aux2;
	int N, E; 

	int max=Integer.MIN_VALUE;
	int min=Integer.MAX_VALUE;
	
	int mark1=0;
	int mark2=0;

	Scanner s = new Scanner(System.in);
	N=s.nextInt();
	E=s.nextInt();

	int[][] dist = new int[N+1][N+1];
	int[] visitado= new int[N+1];
	
	int[] centrais = new int[N+1];
	int[] perifericos =new int[N+1];

	Queue<Integer> q = new LinkedList<Integer>();
	
	ArrayList<LinkedList<Integer>> adj = new ArrayList<LinkedList<Integer>>();
		
	for(int i=0; i<=N; i++){
	    adj.add(new LinkedList<Integer>());
	}
	for(int i=0; i<E; i++){
	    aux1=s.nextInt();
	    aux2=s.nextInt();
	    adj.get(aux1).add(aux2);
	    adj.get(aux2).add(aux1);
	}
	
	//grafo guardado
	
	for(int i=1; i<=N; i++){
	    q.add(i);
	    dist[i][i]=0;
	    visitado[i]=1;
	    while(q.isEmpty()==false){
		aux1 = q.remove();
		for(int j=0;j<adj.get(aux1).size();j++){
		    if(visitado[adj.get(aux1).get(j)]==0){
			q.add(adj.get(aux1).get(j));
			visitado[adj.get(aux1).get(j)]=1;
			dist[i][adj.get(aux1).get(j)]=dist[i][aux1]+1;
			if(dist[i][adj.get(aux1).get(j)] > max)
			    max = dist[i][adj.get(aux1).get(j)];
		    }
		}
	    }
	    Arrays.fill(visitado,0);
	    dist[i][0]=max;   // guarda a excentricidade no primeiro elemento do array
	    max=Integer.MIN_VALUE;
	    
	}
	
	max=Integer.MIN_VALUE;

	for(int i=1; i<=N; i++){
	    if(dist[i][0] > max)
		max = dist[i][0];
	    if(dist[i][0] < min)
		min = dist[i][0];
	}

	for(int i=1; i<=N; i++){
	    
	    if(dist[i][0] == min){
		centrais[mark1]=i;
		mark1++;
	    }
	    if(dist[i][0] == max){
		perifericos[mark2]=i;
		mark2++;
	    }
	}
	System.out.println(max);
	System.out.println(min);
	if(centrais[0]!=0)
	   System.out.print(centrais[0]);
	for(int i=1; i<mark1;i++)
	     System.out.print(" "+centrais[i]);
	System.out.println();

	if(perifericos[0]!=0)
	    System.out.print(perifericos[0]);
	for(int i=1; i<mark2;i++)
	    System.out.print(" "+perifericos[i]);
	System.out.println();
	
	/*
	for(int i=1;i<=N;i++){
	    for(int j=0;j<=N;j++){
		System.out.print(dist[i][j]+" ");
	    }
	    System.out.println();
	}
	
	    */

	    
    }
}
