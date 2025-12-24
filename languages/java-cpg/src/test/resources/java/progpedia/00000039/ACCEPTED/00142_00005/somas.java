import java.util.*;
class somas {
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int[] s = new int[n];
		for(int i=0; i<n; i++)	s[i]=stdin.nextInt();
		int np = stdin.nextInt();
		int[] p = new int[np];
		for(int i=0; i<np; i++) p[i]=stdin.nextInt();
		int[] somas = new int[(n*n-n)/2];
		int k=0;
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				somas[k] = s[i] + s[j];k++;
			}	
		}
		Arrays.sort(somas);
		for(int i=0; i<np; i++) System.out.print(pesquisabinaria(somas, p[i], ((n*n-n)/2)));
		
	}
	
	public static String pesquisabinaria(int v[], int key, int tam){
		return pesquisabinaria(v, 0, tam-1, key);
	}
	
	public static String pesquisabinaria(int v[], int low, int high, int key){
		int middle;
		while(low<high){
			middle = low + (high - low)/2;
			if(key==v[middle]) return v[middle]+"\n"; 
			else if(key > v[middle]) {
				if(middle+1<=high && key < v[middle+1]) {
					if(Math.abs(key-v[middle])==Math.abs(key-v[middle+1])) return v[middle] + " " + v[middle+1]+"\n"; 
					else if(Math.abs(key-v[middle])<Math.abs(key-v[middle+1])) return v[middle] + "\n";
					else return v[middle+1] + "\n";
				} 
				else low=middle+1;
			}
			else {
				if(middle-1>=low && key > v[middle-1]) {
					if(Math.abs(key-v[middle])==Math.abs(key-v[middle-1])) return v[middle-1] + " " + v[middle]+"\n"; 
					else if(Math.abs(key-v[middle])<Math.abs(key-v[middle-1])) return v[middle] + "\n";
					else return v[middle-1] + "\n";
				} 
				else high = middle-1;
			}
		}
		return v[low]+"\n";
	}
}