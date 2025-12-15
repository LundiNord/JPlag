import java.util.*;
import java.io.*;

public class P16 {

   static ArrayList<LinkedList<Integer>> grafo = new ArrayList<LinkedList<Integer>>();
    static int nv, ne;
    static boolean visit[];


    static void dfs (int no){
	visit[no] = true;
	for(int j : grafo.get(no)){
	    if(!visit[j]) dfs (j);
	}
    }


    public static void main(String[] args){
	Scanner stdin = new Scanner (System.in);
	nv = stdin.nextInt()+1;
	ne = stdin.nextInt();
	int conexos = 0;
	visit = new boolean [nv];

	for(int j = 0; j < nv; j++){
	    grafo.add(new LinkedList<Integer>());
	}

	for (int h = 0; h < ne; h++){
	    int a, b;
	    a = stdin.nextInt();
	    b = stdin.nextInt();
	    grafo.get(a).add(b);
	    grafo.get(b).add(a);

	}

	for(int i = 1; i <nv;i++){
	    if(!visit[i]){
		dfs(i);
		conexos++;
	    }
	}

	System.out.println(conexos);
    }

}
