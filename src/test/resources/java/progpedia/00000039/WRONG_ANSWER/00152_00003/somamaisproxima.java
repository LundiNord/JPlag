import java.util.*;
import java.io.*;

class somamaisproxima{
    public static void main(String []args){
	Scanner input=new Scanner(System.in); 

	int n=input.nextInt();
	int q=n*(n-1)/2;
	int somas[]=new int[q];
	int vec[]=new int[n];

	for(int i=0;i<n;i++)
	    vec[i]=input.nextInt();

	int n2=input.nextInt();
	int vec2[]=new int[n2];
	int x = 0;

	for(int i=0;i<n2;i++)
	    vec2[i]=input.nextInt();
	
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		somas[x]= vec[i]+vec[j];
		x++;
	    }
	}
	Arrays.sort(somas);

	
	for(int i=0;i<n2;i++){
	    int pos[]=new int[2];
	    pos = bs(somas,0,q-1,vec2[i]);
	    if (pos[1] != -1)
		System.out.println(somas[pos[0]] + " " + somas[pos[1]]);
	    else
		System.out.println(somas[pos[0]]);
	}
    }
    
	static public int[] bs(int somas[],int low,int high,int key){
	    /*	int nova=0;
		while(low<high){
	   
		int middle=low+(high-low)/2;
		if(somas[middle]>=key) high=middle;
		else low=middle+1;
		}

	
		if(somas[low]>=key)return low;
		if(somas[high]<=key) return high;
	
		return low;
	    */
	    int vec[]=new int[2];
	    int diferencaAnterior = somas[somas.length - 1];
	    int diferencaPosterior = somas[somas.length - 1];
	    int diferencaMeio = somas[somas.length - 1];
	
	    //while(low<high){
	    int middle=low+(high-low)/2;
	    if(middle == 0){
		vec[0]=middle;
		vec[1]=-1;
		return vec;
	    }
	    else if(middle == somas.length-1){
		vec[0]=middle;
		vec[1]=-1;
		return vec;}
	    else {

		diferencaMeio = Math.abs(key - somas[middle]);
		diferencaAnterior = Math.abs(key - somas[middle - 1]);
		diferencaPosterior = Math.abs(key - somas[middle + 1]);

		if(diferencaMeio == diferencaAnterior && diferencaMeio == diferencaPosterior){
		    vec[0]=middle-1;
		    vec[1]=middle;
		    return vec;
		}

		else if(diferencaMeio == diferencaAnterior && diferencaMeio != diferencaPosterior){
		    vec[0] = middle-1;
		    vec[1] = middle;
		    return vec;
		}

		else if(diferencaMeio != diferencaAnterior && diferencaMeio == diferencaPosterior){
		    vec[0] = middle;
		    vec[1] = middle + 1;
		}

		if(diferencaMeio < diferencaAnterior && diferencaMeio < diferencaPosterior){
		    vec[0]=middle;
		    vec[1]=-1;
		    return vec;
		}

		else if(diferencaAnterior <= diferencaPosterior)
		    return bs(somas, low, middle, key);

		else
		    return bs(somas, middle + 1, high, key);
	    }	
	}
    }
