
import java.util.Scanner;

public class InfraEstrutura {

	/**
	 * @param args
	 */
	public static int larMin;
	public static int larMax;
	public static int comMin;
	public static int comMax;
	public static int altMin;

	
	public static void main(String[] args) {
		Scanner b=new Scanner(System.in);

		int nos=b.nextInt();
		b.nextLine();

		larMin=b.nextInt();
		larMax=b.nextInt();
		comMin=b.nextInt();
		comMax=b.nextInt();
		altMin=b.nextInt();

		b.nextLine();
		int origem=b.nextInt();
		int destino=b.nextInt();

		b.nextLine();
		
		int count=0;
		
		while(!b.hasNext("-1")){
			int first=b.nextInt();
			int next=b.nextInt();
			int lar=b.nextInt();
			int comp=b.nextInt();
			int alt=b.nextInt();
			
			if(lar>=larMin && lar<=larMax){
				if(comp>=comMin && comp<=comMax){
					if(alt>=altMin){
						count++;
					}
				}
			}
		
		}
		System.out.println(count);
	}

}


