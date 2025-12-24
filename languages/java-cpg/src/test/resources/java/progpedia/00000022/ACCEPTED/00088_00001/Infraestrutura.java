import java.util.*;

class Infraestrutura{
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int nlocais = stdin.nextInt();
	int largmin = stdin.nextInt();
	int largmax = stdin.nextInt();
	int compmin = stdin.nextInt();
	int compmax = stdin.nextInt();
	int altmin = stdin.nextInt();
	int origem = stdin.nextInt();
	int destino = stdin.nextInt();
	int extrem1 = stdin.nextInt();
	int trocosvalidos = 0;
	while(extrem1 != -1){
	    int extrem2 = stdin.nextInt();
	    trocosvalidos = trocosvalidos + analisaDimensoes(stdin, largmax, largmin, compmax, compmin, altmin);
	    extrem1 = stdin.nextInt();
	}
	System.out.println(trocosvalidos);
    }

    public static int analisaDimensoes(Scanner stdin, int largmax, int largmin, int compmax, int compmin, int altmin){
	int largmaxtroco = stdin.nextInt();
	int compmaxtroco = stdin.nextInt();
	int altmaxtroco = stdin.nextInt();
	int checks = 0;
	if(largmaxtroco >= largmax)
	    checks++;
	else{
	    if(largmaxtroco >= largmin)
		checks++;
	}
	if(compmaxtroco >= compmax)
	    checks++;
	else{
	    if(compmaxtroco >= compmin)
		checks++;
	}
	if(altmaxtroco >= altmin)
	    checks++;
	if(checks < 3)
	    return 0;
	return 1;
    }
}
