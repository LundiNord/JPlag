import java.util.*;
import java.lang.*;

class encomenda{
    public static Scanner scan = new Scanner(System.in);
    public static int largura_minima=0;
    public static int largura_maxima=0;
    public static int comprimento_minimo=0;
    public static int comprimento_maximo=0;
    public static int  altura=0;


    public static void main(String[] args){

	String parametros =scan.nextLine();
	Scanner pp_parame=new Scanner(parametros);
	largura_minima=pp_parame.nextInt();
	largura_maxima=pp_parame.nextInt();
	comprimento_minimo=pp_parame.nextInt();
	comprimento_maximo=pp_parame.nextInt();
	altura=pp_parame.nextInt();


	String line = scan.nextLine();

	while(!line.equals("-1")){
	    line=scan.nextLine();
	    Scanner pronpline = new Scanner(line);

	}
    }



}
