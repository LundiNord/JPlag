import java.util.*;
public class ProbA {
	public static void main(String Args[]){
		Scanner in = new Scanner(System.in);
		int v[]=new int[100000];
		for(int j=0; j!=100000;j++){
			v[j]=0;
		}
		int a=in.nextInt();
		int i=0;
		while(a!=0){
			int k=0;
			int aux=0;
			while(v[k]!=0){
				if(a==v[k]){ aux=a; v[k]=0;}
				else k++;
			}
			if(aux!=0){
				for(int l=k+1;l<100000;l++){
					v[l]=0;
				}
				v[k]=aux;
			}
			else v[i]=a;
			i++;
			a=in.nextInt();
		}
		for(int p=0;p<100000;p++){
			if(v[p]!=0)
			System.out.println(v[p]+" ");
		}
	}
}
