import java.util.Scanner;

public class P005{

    public static void main(String[] args){
	int n; // numero de numeros da sequencia
	int[] seq; // sequencia 
	int counter=0;
	int g = 1;
	
	Scanner stdin = new Scanner(System.in);

	n = stdin.nextInt();
	seq = new int[n];

	for(int i=0; i<n; i++){
	    seq[i]=stdin.nextInt();
	}

	
	for(int i=0;i<n;i++){
	    for(int j=g;j<n;j++)
		if(seq[i]>seq[j])
		    counter += 1;
	    g += 1;
	}
	System.out.println(counter);
    }
}
