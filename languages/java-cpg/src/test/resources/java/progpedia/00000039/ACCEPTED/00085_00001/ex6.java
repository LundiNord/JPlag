import java.io.*;
import java.util.*;

class ex6{
    public static void somar(int v[], int v2[],  int n){
        int k = 0;
        for(int i=0; i<n; i++)
            for(int j=i+1; j<n; j++){
                v[k] = v2[i]+v2[j];
                k++;
            }
    }
    public static void bSearch(int v[], int start, int end, int num){
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
    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int size = ((n-1)*n)/2;
        int v[] = new int[size];
        int guarda[] = new int[n];
        int save=0;
        for(int i=0; i<n; i++)
            guarda[i] = stdin.nextInt();
        somar(v, guarda, n);
        int n2 = stdin.nextInt();
        Arrays.sort(v);
        for(int i=0; i<n2; i++){
            save = stdin.nextInt();
            bSearch(v, 0, size-1, save);
        }
    }
}

//fiz o codigo com a ajuda de dois colegas
	       
		


