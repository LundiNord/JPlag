import java.util.*;


public class teste1 {
	
	public static String par(String linha1, String linha2){
	if(linha1.equals(linha2))
		return "Nenhum";
	
	Scanner verifica1 = new Scanner(linha1);
	verifica1.useDelimiter("");
	Scanner verifica2 = new Scanner(linha2);
	verifica2.useDelimiter("");
	
	while(verifica1.hasNext() && verifica2.hasNext()){
		String a = verifica1.next();
		String b = verifica2.next();
		
		if (!a.equals(b))
			return (a+b);
	}
	return "Nenhum";
}
	
	public static void main (String args[]) {
		Scanner ler = new Scanner(System.in);	
		
	String linha1 = ler.nextLine();
	String linha2 = ler.nextLine();
	
	String resultado = par(linha1, linha2);
	
	System.out.println(resultado);
	
	}
}

