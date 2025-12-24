import java.util.*;

public class CoberturaMinima {
	static int result=0;
	
	public static int proxSubI(int pos_act, int n, int[][] v){
		
		int max=pos_act;
		int value=-1;
		
		for(int i=0; i<n; i++){
			if(v[i][1]>=pos_act && v[i][0]<=pos_act) 
				if(v[i][0]<max){ 
					max=v[i][0];
					value = max;
				}
		}
		
		result++; 
		return value;
	}
	

	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		int m = inp.nextInt(); 
		int n = inp.nextInt(); 
		
		int[][] v = new int[n][2];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<2; j++)
				v[i][j] = inp.nextInt();
		
		int pos_act = proxSubI(m,n,v); 
		
		int pos=-1;
		int key=0;
		
		if(pos_act!=0){
			key=1;
			while(pos!=0){
				pos = pos_act;
				pos_act = proxSubI(pos, n, v);
			}
		}
		
		if(key!=0)
			result--;
		
		System.out.println(result);
		
	}
}
