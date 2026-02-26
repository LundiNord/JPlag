import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int nlocais, lmin, lmax, cmin, cmax, amin, origem, destino, caminho[], valor=0, i, resultado=0;

        Scanner sc = new Scanner(System.in);
        nlocais = sc.nextInt();
        lmin = sc.nextInt();
        lmax = sc.nextInt();
        cmin = sc.nextInt();
        cmax = sc.nextInt();
        amin = sc.nextInt();
        origem = sc.nextInt();
        destino = sc.nextInt();
        caminho = new int [5];
        while(valor != -1){
            valor = sc.nextInt();
            if(valor != -1){
                caminho[0] = valor;
                for(i=1;i<5;i++){
                    caminho[i] = sc.nextInt();
                }
                if(caminho[2]>=lmin && caminho[3]>=cmin && caminho[4]>=amin) resultado +=1;
            }
        }
        System.out.println(resultado);
    }
}
