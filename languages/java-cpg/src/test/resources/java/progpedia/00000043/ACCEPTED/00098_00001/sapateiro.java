import java.util.*;


class sapato implements Comparable<sapato> {
    int n_ordem;
    int dias, multa;

    sapato(int a, int b, int c){
        n_ordem = a;
        dias = b;
        multa = c;
    } 

    public int compareTo(sapato a){
        if(multa*a.dias > a.multa*dias) return -1;
        else if(multa*a.dias < a.multa*dias) return 1;
        else {
            if(n_ordem > a.n_ordem) return 1;
            else return -1;    
        }
    }
}

class sapateiro {
    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        sapato sap[] = new sapato[n];
        for(int i=0; i<n; i++)
            sap[i] = new sapato(i+1, stdin.nextInt(), stdin.nextInt());
        Arrays.sort(sap);
        for(int i=0; i<n-1; i++)
            System.out.print(sap[i].n_ordem + " ");
        System.out.println(sap[n-1].n_ordem);
    }
}