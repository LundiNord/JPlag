import java.util.*;
import java.io.*;

public class optica {
	public static int G[][];
	public static int d[];
	public static int adjsize[];
	public static int w[][];
	public static boolean color;
	
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		int nnos=scn.nextInt();
		int nligacoes=scn.nextInt();
		int custo=scn.nextInt();
		G=new int[nnos+1][nnos-1];
		adjsize=new int[nnos+1];
		w=new int[nnos+1][nnos+1];
		
		for(int i=0; i<nligacoes; i++){
			int x=scn.nextInt();
			int y=scn.nextInt();
			int peso=scn.nextInt();
			G[x][adjsize[x]++]=y;
			G[y][adjsize[y]++]=x;
			w[x][y]=peso;
			w[y][x]=peso;
		}
		
		Prim();
		boolean conectado=true;
		int soma=0;
		
		for(int i=2; i<G.length;i++){
			if(d[i]==Integer.MIN_VALUE){
				conectado=false;
				break;
			}
			soma=soma+d[i];
		}
		
		if (!conectado) System.out.println("impossivel");
			else System.out.println("rendimento optimo: " + (soma - custo*(nnos-1)));
	}
	
	public static void Prim(){
		
		boolean color[] = new boolean[G.length];
		d = new int[G.length];
		for(int i=2; i<G.length; i++){
			d[i]=Integer.MIN_VALUE;
		}
		
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> Q = new PriorityQueue<Integer>(G.length, cmp);
		
		Q.add(1);
		while(!Q.isEmpty()){
			int x=Q.remove();
			if(!color[x]){
				for(int i=0; i<adjsize[x]; i++){
					int k=G[x][i];
					if(!color[k] && d[k]<w[x][k]){
						d[k]=w[x][k];
						Q.add(k);
					}
					
				}
				color[x]=true;
			}
		}
		
	}
	
	public static class Cmp implements Comparator<Integer>{
	    public int compare(Integer x, Integer y){
	        return d[y]-d[x]; 
	    }
	}
}
