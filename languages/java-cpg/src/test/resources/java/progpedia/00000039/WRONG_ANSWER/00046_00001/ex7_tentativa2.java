import java.util.*;

public class ex7_tentativa2 {
    
    public static void main(String[] args) {
     
        Scanner teclado = new Scanner(System.in);

        int i, j, k, tamanho;
     

        //Ler numero de Ss e colocar em vector

        int nS = teclado.nextInt();
        teclado.nextLine();
        int vectorS[] = new int[nS];

        for (i=0; i<nS ; i++) {
            vectorS[i] = teclado.nextInt();
        }

        //Ler numero de Ps e colocar em vector

        int nP = teclado.nextInt();
        teclado.nextLine();
        int vectorP[] = new int[nP];

        for (i=0; i<nP ; i++) {
            vectorP[i] = teclado.nextInt();
        }

  
     
         
            // soma do i + (i+1) e colocar numa nova soma que e soma
            
            int size = nS*(nS)/2;
            int temp;

            int soma[] = new int[size];
            tamanho=0;
         
            for (i = 0; i <nS-1 ; i++)   {
                for (j = i+1; j < nS; j++)       {
                    soma[tamanho]= vectorS[i] + vectorS[j];
                    tamanho++;
                }
            }
             
         
            //System.out.println("---vetor da soma---");
             
            //System.out.println(Arrays.toString(soma));
     
     
            // ordenar vetor soma

            //System.out.println("--Soma Ordenada---");
            Arrays.sort(soma);
            //System.out.println(Arrays.toString(soma));
     
            
     
     
     
     
            // respostas



             
            for (k = 0; k < nP; k++){
                
                //System.out.println("------estou no k " + k + "------");


                int destacia_2, destacia_1, menor_soma = 0, maior_soma = 0;
     
                
                for (i = 1; i < size; i++)      {
                    if ( soma[i] > vectorP[k]){
                        maior_soma = soma[i];
                        menor_soma = soma[i-1];

                        //System.out.println("TESTESTETSETS " + menor_soma + " " + maior_soma + " TESTESTETSE");
                        if (menor_soma > 0) break;
                    }
                }
     
                destacia_1 = maior_soma - vectorP[k];
                destacia_2 = vectorP[k] - menor_soma;
                

                if ( destacia_1 == destacia_2){
                    System.out.println(menor_soma + " " + maior_soma);
                }else if (destacia_1 < destacia_2){
                    System.out.println(maior_soma);
                }else{
                    System.out.println(menor_soma);
                }
         
            }
    
    }

}