import java.io.*;
import java.util.*;

public class QuickSortTeste{
	
	public static int partition(int arr[], int left, int right)
{
      int i = left, j = right;
      int tmp;
      int pivot = arr[(left + right) / 2];
      while (i <= j) {
            while (arr[i] < pivot)
                  i++;
            while (arr[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
      };
      return i;
}

 

 public static void quickSort(int arr[], int left, int right) {
      int index = partition(arr, left, right);
      if (left < index - 1)
            quickSort(arr, left, index - 1);
      if (index < right)
            quickSort(arr, index, right);
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
			quickSort(v, 0, v.length-1);
			int i;
			StringBuffer result = new StringBuffer();
			for(i = 0; i < v.length-1; i++){
				result.append(v[i] + " ");
			}
			result.append(v[i]);
			String mynewstring = result.toString();
			System.out.println(mynewstring);
			bi.close();
		} catch (IOException e){}
	}
}