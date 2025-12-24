import java.util.*;

public class Sapateiro {

	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		int n = inp.nextInt();
		
		float[] v= new float[n+1];
		
		for(int i=1; i<=n; i++){
			float d = inp.nextInt();
			float m = inp.nextInt();
			v[i]=m/d;
		}
		
		int conta=0;
		int index=-1;
		
		while(conta!=n){
			float min = 0;
			for(int i=1; i<=n; i++){
				if(v[i]>min){
					min=v[i];
					index=i;
				}
				else if(v[i]==min){
					if(i<index){
						min=v[i];
						index=i;
					}
				}
			}
			
			conta++;
			if(conta!=n)
				System.out.print(index + " ");
			else
				System.out.println(index);
			v[index]=-1;
		}
		
		
	}
}
