import java.io.*;
import java.util.*;

class Cigas{
    public static void main (String[] args){

	Scanner scan= new Scanner(System.in);
	int indice=0;
	int n=scan.nextInt();
	int lista[]= new int[1000];
	int f[]= new int[1000];
	//lista[0]=n;
	int count=0;
	int c=0;
	while(n!=0){
		lista[count]=n;
		n=scan.nextInt();
		
		count++;
	}
	int tam=count;
		
	for(int i=0;i<tam;i++){
		for(int j=tam-1;j>i;j--){
			if(lista[i]==lista[j]){
				f[c]=lista[j];
				c++;	
				i=j+1;	
			}
		}
	f[c]=lista[i];
	c++;
	}
	for(int k=0;k<c;k++){
		System.out.println(f[k]);
	}
	//	
    }
}
