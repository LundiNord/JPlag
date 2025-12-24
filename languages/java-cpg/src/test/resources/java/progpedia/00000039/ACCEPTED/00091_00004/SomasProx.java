/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bonifacio
 */

import java.util.*;


public class SomasProx {
    
    public static int[] removeZero (int v[]){
        List <Integer> list = new ArrayList<Integer>();
        int k = 0;
        for(int i = 0; i < v.length; i++){
            if (v[i] != 0){
                list.add(v[i]);
                k++;
            }
        }
        int newV[] = new int[k];
        for (int i = 0; i < k; i++)
            newV[i] = (Integer) list.get(i);
        return newV;
    }
    public static void bsearch(int p, int sum[]){
        int low = 0;
        int high = sum.length-1;
        int mid=0;
        while(high - low > 1){
            mid = low + (high-low) / 2;
            if(sum[mid] < p) low = mid;
            else high = mid;
        }
        if (p - sum[low] == sum[high] - p && sum[low] != p && sum[high] != p) System.out.println(sum[low] + " " + sum[high]);
        else {
            if( p - sum[low] < sum[high] - p) System.out.println(sum[low]);
            else System.out.println(sum[high]);     
        }
    }
    
    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
        int j = 0;
        int n = stdin.nextInt();
        int s[] = new int [n];
        int sum[] = new int [n*n];
        for(int i=0; i < n; i++) s[i] = stdin.nextInt();
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                if(s[i] != s[k]){	
                    sum[j] = s[i] + s[k];	
                    j++;
		}
            }
	}
	Arrays.sort(sum); 
	int[] sum2 = removeZero(sum);
        int p = stdin.nextInt();
	for(int i = 0; i < p; i++){
	    bsearch(stdin.nextInt(), sum2); 
	}	
    }
}
