import java.util.*;

class cigarras{

   static  int insert(int[] x,int y,int current){
	int i;
	for(i = 0; i < current; i++){
	    if(x[i] == y){
		return i;
	    }
	}
	x[current+1] = y;
	return current+1;
	
    }

    public static void main(String [] args){

	int [] visited = new int[30];
	int currentPos = -1;
	int latest;
	int x;
	Scanner sc = new Scanner(System.in);
	latest = sc.nextInt();
	
	while (latest != 0){
	    currentPos = insert(visited,latest,currentPos);
	    latest = sc.nextInt();
	}
	for(x=0;x<=currentPos;x++){
	    System.out.println(visited[x]);
	}
	    
	
    }
}
