import java.util.*;

class prob03{
    public static void main(String[] args){
	Scanner in= new Scanner(System.in);
	int N = in.nextInt();
	int n[]=new int[N];
	for(int i=0;i<N;i++){
	    n[i]=in.nextInt();
	}
	Arrays.sort(n);
	for(int i=0;i<N;i++){
	    System.out.println(n[i]);
	}
    }
}
