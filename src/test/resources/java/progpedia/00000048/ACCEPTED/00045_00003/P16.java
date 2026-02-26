
import java.util.*;


class P16{
	public static void main(String[] args) {
		
			Scanner in = new Scanner(System.in);

			int nNos=in.nextInt();    // numero de nos
			int nLigacoes=in.nextInt();  // numero de arestas

			boolean ligacoes[][] = new boolean[nNos+1][nNos+1];  // matriz ligacoes que diznos as arestas que estao ligados

			for (int i=0; i<nLigacoes; i++) {
					
				int ptx = in.nextInt();
				int pty = in.nextInt();
				ligacoes[ptx][pty]=true;
				ligacoes[pty][ptx]=true;
			}

			/*for (int i=0; i<nNos+1; i++) {
				for (int j=0; j<nNos+1; j++) {
					System.out.print(ligacoes[i][j] + " ");
				}System.out.println();
			}*/

			boolean nosVisitados[] = new boolean[nNos+1];
			for (int i=0; i<nNos; i++) {
				nosVisitados[i]=false;
			}

			

			int count=0;
			for (int i=1; i<nNos+1; i++) {
				if (nosVisitados[i] == false) {
					count +=1;
					DFS(ligacoes,nosVisitados,nNos,i);
				}
			}

			System.out.println(count);
	}

	public static void DFS( boolean ligacoes[][], boolean nosVisitados[],int nNos, int no){

		nosVisitados[no] = true;

		for (int j=1; j<nNos+1; j++) { // esta a percorrer todas as nos as suas adijacentes todas ou ligacoes com esse no
		
			if ( ligacoes[no][j] == true  && nosVisitados[j]== false) { // quer dizer que a uma ligacoes do no anterior e seguinte
				DFS(ligacoes,nosVisitados,nNos,j);
			} 
		}
	}
}


