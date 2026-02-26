
import java.util.*;

public class Piramide {
	static int conta=0;
	
	static void ir(int piramide[][],int n){
		for(int i=n-1;i>=0;i--){
			for(int j=0;j<n;j++){
				if(piramide[i][j]!=0 && piramide[i][j]!=10 && i>0 && j<n-1 && piramide[i-1][j+1]!=0){
						piramide[i-1][j+1]+=piramide[i][j];
						if(i==n-1)
							conta++;
				}
				
				if(piramide[i][j]!=0 && piramide[i][j]!=10 && i>0 && j>0 && piramide[i-1][j]!=0){
						piramide[i-1][j]+=piramide[i][j];
						if(i==n-1)
							conta++;
				}
				
			}
		}
	}
	
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		int n = input.nextInt();
		int piramide[][]=new int[n][n];
		
		for(int i=0;i<n;i++)
			piramide[n-1][i]=1;
			
		int d=input.nextInt();
		for(int i=0;i<d;i++)
			piramide[n-input.nextInt()][input.nextInt()]=10;
			
		int temp=1;
		for(int i=n-2;i>=0;i--){
			for(int j=temp;j<n;j++){
				if(piramide[i][j]==10)
					piramide[i][j]=0;
				else
					piramide[i][j]=1;
			}
			temp++;
		}
		
		//transformalo numa piramide
		/*
		for(int i=n-2;i>=0;i--){
			for(int j=temp;j<n;j++){
				if(j>0)
					piramide[i][j-1]=piramide[i][j];
				if(j==n-1)
					piramide[i][j]=0;
			}
		}*/
		
		ir(piramide,n);
		
		/*for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				System.out.print(piramide[i][j]+" ");
			System.out.println();
		}*/
		

		System.out.println(piramide[0][n-1]-d*d);
	}
}
 
