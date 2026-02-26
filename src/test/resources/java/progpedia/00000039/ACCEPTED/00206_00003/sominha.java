import java.io.*;
import java.util.*;

class sominha {
//Funcoes sum e bs
    public static void sum(int s[], int aux[], int n) {
    int x = 0;

    for(int i = 0; i<n-1; i++)
        for(int j = i+1; j < n; j++) {
            s[x] = aux[i] + aux[j];
            x++;
        }
    }

public static void bs(int s[], int start, int end, int value) {

    int middle;
    int right=-1;

    if(value >= s[end])
        System.out.println(s[end]);
    else if(value <= s[start])
        System.out.println(s[0]);
    else { //comparacoes para fazer a pesquisa
        while(start < end){
            middle = start + (end - start)/2;
            if(s[middle] >= value)
                end = middle;
            else 
                start = middle + 1;
        }
        int left = Math.abs(s[start-1] - value);
        if(start + 1 <= end)
            right = Math.abs(s[start+1] - value);
        int center = Math.abs(s[start] - value);
        if(left == center && s[start-1] != s[start]) {
            System.out.println(s[start-1] + " " + s[start]);
            return;
        }
        else if(right != -1 && center == right && s[start] != s[start-1]) {
            System.out.println(s[start] + " " + s[start+1]);
            return;
        }
        else if(left < center) {
            System.out.println(s[start-1]);
            return;
        }
        else if(right != -1 && right < center) {
            System.out.println(s[start+1]);
            return;
        }
        else {
            System.out.println(s[start]);
            return;
        }
    }
}

public static void main(String args[]) {
    Scanner in = new Scanner(System.in);

    int n = in.nextInt();  //Ler o primeiro numero
    int size = ((n-1)*n)/2; //nº combinacoes de todas as somas possiveis
    int s[] = new int [size]; //vector que guarda as somas calculadas
    int read[] = new int[n]; //vector que lê os primeiros valores 
    int iii = 0; //valor que vai ser procurado

    for(int i = 0; i<n; i++) 
        read[i] = in.nextInt();

    sum(s, read, n); //funcao que calcula todas as somas

    int m = in.nextInt(); //ler osegundo numero

    Arrays.sort(s); //ordenar os valores das somas

    for(int i = 0; i<m; i++) {
        iii = in.nextInt();
        bs(s, 0, size-1, iii); //procurar em s, comecando em 0 ate size-1, o valor in        
        }
    }
}