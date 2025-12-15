import java.util.*;
public class CigarrasTontas {
    public static void main(String[] args) {
        int locaisVisitados [] = new int [30], x, local=1;
        Scanner input =new Scanner (System.in);
        
        while (local!=0){
            x=0;
            local=input.nextInt();
            for (int i=0; i<=29; i++){
                if (locaisVisitados[i]==local){
                    apagaLocais(i+1,29,locaisVisitados);
                    x=1;
                    break;}
            }
            if (x!=1){
                for (int i=0; i<=29; i++){
                    if (locaisVisitados[i]==0){
                        locaisVisitados[i]=local;
                        break;}
                }
            }
        }
        for (int i : locaisVisitados){
            if (i!=0)
                System.out.println(i);
        }
    }
    
    static void apagaLocais(int a, int b, int v[]){
        for (int i=a; i<=b; i++)
            v[i]=0;
    }
}