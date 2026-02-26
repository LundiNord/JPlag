import java.util.*;
import java.io.*;


public class SomasProx{

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
		/*System.out.println("start: "+start+" v="+v[start]);
		  System.out.println("mid: "+mid+" v="+v[mid]);
		  System.out.println("end: "+end+" v="+v[end]);
		  try{
		  Thread.sleep(5000);
		  }catch(InterruptedException ex){
		  Thread.currentThread().interrupt();
		  }*/
		if(v[mid] >= num) end=mid;
		else start=mid+1;
	    } 
		
	    int esquerda = Math.abs(v[start-1] - num);
	    if(start+1<=end) direita = Math.abs(v[start+1] - num);
	    int centro = Math.abs(v[start] - num);
	    
	    /*System.out.println("start: "+start+" v="+v[start]);
	      System.out.println("mid: "+mid+" v="+v[mid]);
	      System.out.println("end: "+end+" v="+v[end]);
	      System.out.println("vai fazer agora as avaliacoes");*/
		
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

