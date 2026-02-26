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

    public static void bSearch(int v[], int start, int end, int cond){
	int mid=start;
	int direita;
	int esquerda;
	int centro;
	if(cond >= v[end])
	    System.out.println(v[end]);
	else if(cond <= v[start])
	    System.out.println(v[0]);
	else{
	    while(start<end){
		mid = start+(end-start)/2;
		/*System.out.println("start: "+start+" v="+v[start]);
		System.out.println("mid: "+mid+" v="+v[mid]);
		System.out.println("end: "+end+" v="+v[end]);
		try{
		Thread.sleep(7000);
		}catch(InterruptedException ex){
		    Thread.currentThread().interrupt();
		    }*/
		if(v[mid] >= cond) end=mid;
		else start=mid+1;
	    } 
	    mid=(start+end)/2;
	    if(start >= cond){
		if(v[mid] == cond) {System.out.println(v[mid]);return;}
	    }
	    else{
		/*System.out.println("start: "+start+" v="+v[start]);
		System.out.println("mid: "+mid+" v="+v[mid]);
		System.out.println("end: "+end+" v="+v[end]);
		System.out.println("vai fazer agora as avaliacoes");*/
		esquerda = Math.abs(v[mid-1] - cond);
		//direita = Math.abs(v[mid+1] - cond);
		centro = Math.abs(v[mid] - cond);
		try{
		    if(esquerda == centro && v[mid-1] != v[mid]){
			System.out.println(v[mid-1]+" "+v[mid]);
			return;
		    }
		}
		catch(Exception e){}

		/*try{
		    if(centro == direita && v[mid] != v[mid+1]){
			System.out.println(v[mid]+" "+v[mid+1]);
			return;
		    }
		}
		catch(Exception e){}*/
		try{
		    if(esquerda < centro){
			System.out.println(v[mid-1]);
			return;
		    }
		}
		catch(Exception e){}
	    
		/*try{
		    if(centro < Math.min(esquerda,direita)){
			System.out.println(v[mid]);
			return;
		    }
		}
		catch(Exception e){}
	    
		try{
		    if(direita < centro){
			System.out.println(v[mid+1]);
			return;
		    }
		}
		catch( Exception e){}*/
	    
		System.out.println(v[mid]);
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
	    bSearch(v, 0, size-1,save);
	}
    }
}
