import java.util.*;
class main_A {
	public static void main(String[] args) 
	{
		Scanner stdin = new Scanner (System.in);
		int nVertices = stdin.nextInt();
		int Lmin = stdin.nextInt(); 
		int Lmax = stdin.nextInt(); 
		int Cmin = stdin.nextInt(); 
		int Cmax = stdin.nextInt();
		int Amin = stdin.nextInt();
		int origem = stdin.nextInt();
		int destino = stdin.nextInt();
		int temporigem = 1;
		int cont =0;
		while(temporigem!=-1)
		{
			temporigem= stdin.nextInt();
			//System.out.println("aqui "+ temporigem+ " cont de " + cont);
			if (temporigem!=-1)
			{
				int tempdestino = stdin.nextInt();
				int tempL = stdin.nextInt();
				int tempC = stdin.nextInt();
				int tempA = stdin.nextInt();
				if ( (tempL>=Lmin) && (tempC>=Cmin )&& (tempA>=Amin))
				{
					//System.out.println(" origem " + temporigem+ " destino "+ tempdestino);
					cont+=1;
				}
			}
			
		}
		System.out.println(cont);
	}
}
