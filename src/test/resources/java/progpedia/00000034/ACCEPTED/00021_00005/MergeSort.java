import java.util.Arrays;
import java.io.*;

public class MergeSort{

	static void merge(int left[], int right[], int [] result){
		int i = 0, j = 0, k = 0;
		while(j < right.length && i < left.length){
			if(left[i] > right[j]){
				result[k++] = right[j++];
			}
			else{
				result[k++] = left[i++];
			}
		}
		for(int temp = i; temp < left.length; temp++){
			result[k++] = left[temp];
		}
		for(int temp = j; temp < right.length; temp++){
			result[k++] = right[temp];
		}
	}

	static int[] mergesort(int v[]) {
		if(v.length <= 1){
			return v;
		}
		int[] left = new int[v.length/2];
		int[] right = new int[v.length - left.length];
		System.arraycopy(v, 0, left, 0, left.length);
		System.arraycopy(v, left.length, right, 0, right.length);
		mergesort(left);
		mergesort(right);
		merge(left, right, v);
		return v;
	}

	public static void main(String[] args){
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		
		
		try{
			int lenArray = Integer.parseInt(bi.readLine());
			int v[] = new int[lenArray];
			String line;
			line = bi.readLine();
			int k = 0;
			for(String i: line.split(" ")){
				v[k++] = Integer.parseInt(i);
			}
			v = mergesort(v);
			int i;
			StringBuffer result = new StringBuffer();
			for(i = 0; i < v.length; i++){
				result.append(v[i] + " ");
			}
			String mynewstring = result.toString();
			System.out.println(mynewstring);
			bi.close();
		} catch (IOException e){}
	}
}