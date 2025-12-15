import java.util.Scanner;


public class infraestrutura {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int count=0;
		int nvs=in.nextInt();
		int largmin = in.nextInt();
		int largmax=in.nextInt();
		int compmin=in.nextInt();
		int commax=in.nextInt();
		int altura=in.nextInt();
		int origem=in.nextInt();
		int destino=in.nextInt();
		
		int troco=in.nextInt();
		while(troco!=-1){
			int troco2=in.nextInt();
			int largmaxl=in.nextInt();
			int compmaxl=in.nextInt();
			int alturamaxl=in.nextInt();
			if(largmaxl>=largmin && compmaxl>=compmin  && alturamaxl>=altura){
				count++;
			}
			troco=in.nextInt();
		}
		
		System.out.println(count);
	}
}