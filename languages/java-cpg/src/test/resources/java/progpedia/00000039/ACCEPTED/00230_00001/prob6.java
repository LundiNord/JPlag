import java.util.*;
import java.math.*;

public class prob6{
    
    static int n;
    static int p;
    static int v[];
    static int somas[];
    static int pares[];
    static int cont_somas;
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        v = new int[n];
        int k = 0;
        cont_somas = ((n-1)*n)/2;
        somas = new int[cont_somas];
        for(int i = 0; i<n; i++){
            v[i] = in.nextInt();
        }
        for(int i = 0; i<n; i++)
            for(int j = i+1; j<n; j++) somas[k++] = v[i] + v[j];
        Arrays.sort(somas);
        p = in.nextInt();
        pares = new int[p];
        for(int i = 0; i<p; i++) pares[i] = in.nextInt();
        for(int i = 0; i<p; i++) pesquisa(somas, 0, cont_somas-1, pares[i]);
    }
    
    public static void pesquisa(int v[], int start, int end, int num){
        int mid=start;
        int direita=-1;
        if(num >= v[end])
            System.out.println(v[end]);
        else if(num <= v[start])
            System.out.println(v[0]);
        else{
            while(start<end){
                mid = start+(end-start)/2;
                if(v[mid] >= num) end=mid;
                else start=mid+1;
            }
            int esquerda = Math.abs(v[start-1] - num);
            if(start+1<=end) direita = Math.abs(v[start+1] - num);
            int centro = Math.abs(v[start] - num);  
            if(esquerda == centro && v[start-1] != v[start]){
                System.out.println(v[start-1]+" "+v[start]);
                return;
            }
            else if(direita!=-1 && centro == direita && v[start] != v[start+1]){
                System.out.println(v[start]+" "+v[start+1]);
                return;
            }
            else if(esquerda < centro){
                System.out.println(v[start-1]);
                return;
            }  
            else if(direita!=-1 && direita < centro){
                System.out.println(v[start+1]);
                return;
            }
            else{
                System.out.println(v[start]);
                return;
            }
        }
    }
}