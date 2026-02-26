import java.io.*;
import java.util.*;

public class somas{
    /*PEsquisa binaria norma
    public static int bsearch(int v[],int k){
	int low=0;
	int high=v.length;
	while(low<high){
	    int middle=low+(high-low)/2;
	    if(v[middle]==k) return v[middle];
	    else if(k<v[middle])high=middle;
	    else low=middle+1;
	}
	return low;


    }
    */
    


    
    public static void main(String args[]){

	Scanner stdin= new Scanner(System.in);

	int n=stdin.nextInt();
	int v[]=new int[n];
	int somas[]=new int[(n*(n-1))/2];

	//Ler dados
	for(int i=0;i<n;i++)
	    v[i]=stdin.nextInt();

	//calcular somas possiveis
	int pos=0;
	for(int i=0;i<n-1;i++){
	    for(int j=i+1;j<n;j++){
		somas[pos++]=v[i]+v[j];
	    }
	}

	Arrays.sort(somas);

	//tratar das perguntas
	int p=stdin.nextInt();
	int pi;
	for(int i=0;i<p;i++){
	    pi=stdin.nextInt();
	    int low=0;
	    int high=somas.length-1;
	    int middle=(low+high)/2;
	    do{
		if(somas[middle]>pi){
		    low=low;
		    high=middle;
		}
	    
		else {
		    low=middle;
		    high=high;
		}

		middle=(low+high)/2;
	    }
	    while(high>low+1);

	    if(Math.abs(somas[low]-pi)<Math.abs(somas[high]-pi))
		System.out.println(somas[low]);
	    else if(Math.abs(somas[low]-pi)>Math.abs(somas[high]-pi))
		System.out.println(somas[high]);
	    else System.out.println(somas[low] +" "+somas[high]);
	  

	 
	}



    }
}
