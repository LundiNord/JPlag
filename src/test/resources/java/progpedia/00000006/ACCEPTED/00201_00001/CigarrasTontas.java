import java.util.Scanner;

public class CigarrasTontas {

	/**
	 * @param v - vector com o caminho
	 */
	static void escreve(int v[],int c){
		for(int i=0;i<c;i++)
			System.out.println(v[i]);
	}
	/**
	 * 
	 * @param v - vector com o caminho
	 * @param c - cursor 
	 * @param l - local para inserir no vector
	 * @return valor do cursor depois de encontrar a primeira ocorrencia de l
	 */
	static int trata(int v[],int c,int l){
		for(int i=0;i<c;i++)
			if(l == v[i])
				c=i;
		v[c]= l;
		return c;
	}

	public static void main(String[] args) {
		Scanner ler=new Scanner(System.in);
		int local=ler.nextInt(),cursor=0;
		int caminho[] = new int[30];
		while(local!= 0){
			cursor=trata(caminho,cursor,local);
			cursor++;
			local=ler.nextInt();
		}
			escreve(caminho,cursor);
	ler.close();
	}
}
