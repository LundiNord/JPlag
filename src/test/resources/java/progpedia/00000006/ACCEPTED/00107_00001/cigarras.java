import java.util.*;

class cigarras{
	public static void main(String[] args){
		int[] v = new int[30];
		Scanner sc = new Scanner(System.in);
		int i=0;
		
		while((v[i] = sc.nextInt()) != 0){
			for(int j=0;j<i;j++){
				if(v[i] == v[j])
					i = j;
			}
			i++;
		}
		
		for(int k=0;k<i;k++)
			System.out.println(v[k]);
			
		return;
	}
}
