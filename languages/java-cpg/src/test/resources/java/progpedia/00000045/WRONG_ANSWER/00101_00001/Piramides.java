import java.util.*;

public class Piramides {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int nCamadas = sc.nextInt();
		int camada [] = new int[nCamadas];
		Arrays.fill(camada, 1);
		int nPedrasDeterioradas = sc.nextInt();
		int camadaDeteriorada[] = new int[nPedrasDeterioradas];
		int posDeteriorada[] = new int[nPedrasDeterioradas];
		int i = 0;
		int pedras = nPedrasDeterioradas;
		while(pedras-- > 0){
			camadaDeteriorada[i] = sc.nextInt();
			posDeteriorada[i++] = sc.nextInt();
		}
		i = 0;
		pedras = nCamadas-1;
		while(nCamadas-- > 0){
			for(int j = 0; j < nPedrasDeterioradas; j++){
				if(i+1 == camadaDeteriorada[j]){
					camada[posDeteriorada[j]-1] = 0;
				}
			}
			i++;
			for(int j = 0; j < pedras; j++){
				camada[j] = camada[j] + camada[j+1];
			}
			pedras--;
		}
		System.out.println(camada[0]);
	}
}