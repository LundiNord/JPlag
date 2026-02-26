import java.util.*;

class prob05{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int N= in.nextInt();
	int n[]= new int[N];
	for(int i=0;i<N;i++){
	    n[i]=in.nextInt();
	}
	int inv=0;
	for(int i=0;i<N;i++){
	    for(int j=i+1;j<N;j++){
		if(n[i]>n[j] && i<j){
		    inv++;
		}
	    }
	}
	System.out.println(inv);
    }
}
