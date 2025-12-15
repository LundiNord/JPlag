import java.util.*;

class cigarra{
	static int[] v;
	
	public static void main(String args[]){
		int i=1;
		Scanner sc = new Scanner(System.in);
		
		v = new int[30];
		
		v[0] = sc.nextInt();
		v[1] = sc.nextInt();
		
		while(v[i]!=0){
			int ind = procurarValor(v[i],i);
			if(ind != -1)
				i=ind;
			v[++i] = sc.nextInt();
		}
		
		for(int j=0;j<i;j++)
			System.out.println(v[j]);
	}
	
	public static int procurarValor(int val, int len){
		for(int k=0;k<=len;k++)
			if(v[k]==val) return k;
		
		return -1;
	}
}