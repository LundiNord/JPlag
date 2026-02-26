import java.util.*;
import java.io.*;

class Inversoes{
    static int contador;

    public static void merge2(int[] array){
	int[] tmp = new int[array.length];
	merge2(array,tmp,0,array.length-1);
    }
    private static void merge2(int[] array,int[] tmp,  int left, int right){
	if(left<right){
	    int half = (left+right)/2;
	    merge2(array,tmp,left,half);
	    merge2(array,tmp,half+1,right);
	    merge(array,tmp,left,half+1,right);
	}
    }

    private static void merge(int[] array, int[] tmp,int left, int right, int rightEnd){
	int leftEnd=right-1;
	int k=left;
	int num=rightEnd -left +1;
	while(left<=leftEnd && right<=rightEnd){
	    if(array[left]<=array[right]){
		tmp[k]=array[left];
		k++;
		left++;
	    }
	    else{
		contador = contador + (leftEnd-left +1);
		tmp[k]=array[right];
		k++;
		right++;
	    }
	}
	while(left <= leftEnd){
	    tmp[k]=array[left];
	    k++;
	    left++;
	}
	while(right<=rightEnd){
	    tmp[k]=array[right];
	    k++;
	    right++;
	}
	for(int i=0;i<num;i++, rightEnd--){
	    array[rightEnd] = tmp[rightEnd];
	}
    }
    
    public static void main(String[] args){
	contador=0;
	Scanner hello = new Scanner(System.in);
	int N = hello.nextInt();
	int[] array = new int[N];
	for(int i=0; i<N; i++){
	    int x = hello.nextInt();
	    array[i]=x;
	}
	merge2(array);
	System.out.println(contador);
    }
}
