import java.util.Scanner;

public class Invs {

    static int n;
    static int val[];
    static int quant = 0;

    static void lerInput(){
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	val = new int[n];
	for(int i = 0; i <n; i++)
	    val[i] = stdin.nextInt();
    }

    static void solve(int inicio, int fim){
	if((fim - inicio) != 1){
	    int meio = ((fim-inicio)/2)+inicio;
		solve(inicio, meio); solve(meio, fim);
		for ( int j = inicio ; j <meio; j++)
		    for ( int i = meio; i < fim; i++)
			if(val[j]>val[i])
			    quant++;
	}

    }



    
   public static void main(String args[]){
	lerInput();
	solve(0,n);
	System.out.println(quant);
    }


}
