import java.util.*;
import java.io.*;

class Sapatos{
	float euros;
	float multa;
	float index;

	public Sapatos(float euros, float multa){
		this.euros=euros;
		this.multa=multa;
		index=(euros/multa);
	}
}

public class p10{
	public static void main(String args[]){

		Scanner input  = new Scanner(System.in);
		int n = input.nextInt();
		Sapatos[] client = new Sapatos[n+1];
		int[] positionClient = new int[n+1];

		for(int i=1; i<=n;i++)
			client[i]= new Sapatos(input.nextInt(),input.nextInt());

		float worstClient = client[1].index;     //    0.0001    /    1   
		int indexWorstClient = 1;

		for(int i = 0; i <=n; i++){
			for(int j=1; j<=n; j++){

				if( client[j].index < worstClient){
				worstClient = client[j].index;
				indexWorstClient = j;
				}
			}

			client[indexWorstClient].index = 1000000;
			worstClient = client[indexWorstClient].index;
			positionClient[i]=indexWorstClient;

		}

		for(int i = 0; i < n; i++)
			System.out.print(positionClient[i] + " ");
	}
}