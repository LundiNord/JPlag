import java.util.Scanner;


public class Cigarras {
	public static void main(String[] args) {
		Scanner ex= new Scanner (System.in);
		int ponto=ex.nextInt();
		int percurso[] = new int [30];
		int nr=0;
		while(ponto!=0){
			for(int i=0;i<nr;i++){
				if(percurso[i]==ponto){
					 nr=i;
				}
			}
			percurso[nr]=ponto;
			nr++;
			ponto=ex.nextInt();
		}
		for(int i=0;i<nr;i++){
			System.out.println(percurso[i]);
		}
	}
}
