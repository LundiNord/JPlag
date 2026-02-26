import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Prob06 {
	public static void main (String []args){
		Scanner stdin = new Scanner (System.in);
		int n= stdin.nextInt(); //tamanho do conjunto S
		int []v = new int [n];  //conjunto S
		for(int i=0;i<n;i++){
			v[i]= stdin.nextInt();
		}
		int p= stdin.nextInt();
		int []per = new int [p]; //vector de perguntas
		
		//Somas
		int []somas = fazerSoma(v); //somas possiveis
		Arrays.sort(somas);    //ordenar vector das somas
		
		for(int i=0;i<per.length;i++){
			per[i]=stdin.nextInt();
			int []bs = bsearch(somas,0,somas.length-1,per[i]);
		}
	}

	private static int[] bsearch(int[] somas, int low, int high, int key) {
		// TODO Auto-generated method stub
		int middle; 
		int dif = Integer.MAX_VALUE,tdif;
		int a[] = new int [2];
		a[0] = a[1] = -1;
		while(low <= high) {
			middle = low + (high - low)/2;
			tdif = Math.abs(key - somas[middle]);
			
			if(tdif < dif) {
				dif =tdif;
				a[0] = somas[middle];
				a[1] = -1;
			}
			if(tdif == dif && a[0] !=somas[middle])
				a[1]=somas[middle];
			
			if(key == somas[middle]){
				a[0] = somas[middle];
				a[1]=-1;
				return a;
			}
			else if(key < somas[middle])
				high = middle -1;
			else low = middle +1;
		}
		return a;
	}

	private static int[] fazerSoma(int[] v) {
		// TODO Auto-generated method stub
		int count=0;
		for(int i=0;i<v.length;i++){
			count = count + i;
		}
		int []somas = new int [count];
		int k=0;
		for(int i=0;i<v.length-1;i++)
			for(int j=i+1;j<v.length-2;j++)
				somas[k++]= v[i] + v[j];
		return somas;
	}
}
