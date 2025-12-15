import java.util.*;
import java.io.*;
public class Sapateiro{
    public static float duration[] , fee[], sequence[];
    public static void main(String [] args){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	duration = new float[n];
	fee = new float[n];
	sequence = new float[n];
        for(int i = 0 ; i<n ; i++){
	    duration[i] = in.nextInt();
	    fee[i] = in.nextInt();
	}

	//Processs
	for(int i = 0 ; i<n ; i++)
	    sequence[i] = (fee[i]/duration[i]);
	for(int i = 0 ; i<n ; i++){
	    int maior = 0 ;
		for(int j = 0 ; j<n ; j++){
		    if(sequence[j] > sequence[maior] && sequence[j] != -1)
			maior=j;
		}
		if(i == n-1)
		    System.out.println(maior+1);
		else
		    System.out.println(maior+1 + " " );
	}
    }
}
