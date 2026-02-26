import java.util.*;

public class piramide {
	static int max=1000;
	static int n;
	static int piramide[][]=new int [max][max];

	static void constroi(){
		int c,p;
		for (c=2;c<n+1;c++){
			for (p=1;p<n-c+1+1;p++){
				if(piramide[c][p]==-1)
					piramide[c][p]=0;
				else
					piramide[c][p]=piramide[c-1][p]+piramide[c-1][p+1];
			}
		}
	}


	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		int d = input.nextInt();
		
		int c,p;
		
		for(int i=0;i<d;i++){
			c=input.nextInt();
			p=input.nextInt();
			piramide[c][p]=-1;
		}
		for(int i=1;i<n+1;i++){
			if(piramide[1][i]!=-1)
				piramide[1][i]=1;
			else
				piramide[1][i]=0;
		}
		constroi();
		System.out.println(piramide[n][1]);
	}
}
