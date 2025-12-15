import java.io.*;

public class P3 {

	public static int contador=0;

	public static void main(String[] args) throws IOException{

		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(teclado.readLine());
		int vector[] = new int[n];

		String temp[] = teclado.readLine().split(" ");


		for (int i=0; i<n ; i++)
			vector[i] = Integer.parseInt(temp[i]);

		//Ordenar o vector
		mergesort(vector,0,n-1);


		//Resposta
		// for (int i=0; i<n-1 ; i++)
		// 	System.out.print(vector[i]+ " ");
		// System.out.println(vector[n-1]);

		System.out.printf("%d\n",contador);
	}

	public static void merge(int v[], int start, int middle, int end) {

		int comp = end+1;
		int array[] = new int[comp];
		int a=start;
		int b=middle+1;
		int i=start;

		for(i=start;i<=end;i++){

			if( (a<=middle) && (b<=end) && (v[a]<=v[b]) ){
										// System.out.println("----if1----");
										// System.out.println(contador);
				array[i]=v[a];
										// System.out.println(Arrays.toString(array));

				a++;
			}
			
			else if( (a<=middle) && (b<=end) && (v[a]>v[b]) ){
				contador += ((middle-a)+1);

				// System.out.println("OLA " + middle + "    " + a  + "     diferenca: " + ((middle-a)+1)   );

										// System.out.println("----if2----");
										// System.out.println(contador);
				array[i]=v[b];
										// System.out.println(Arrays.toString(array));

				b++;
			}
			
			else if((a>middle)&&(b<=end)){
										// System.out.println("----if3----");
										// System.out.println(contador);
				array[i]=v[b];
										// System.out.println(Arrays.toString(array));

				b++;
			}
			
			else{
										// System.out.println("----if4----");
										// System.out.println(contador);
				array[i]=v[a];
										// System.out.println(Arrays.toString(array));

				a++;
			}

		}

		for(i=start; i<=end;i++)
			v[i] = array[i];

	}

	public static void mergesort(int v[], int start, int end){

		int middle = (start+end)/2;
		if(start==end)  return;
		mergesort(v, start, middle);
		mergesort(v, middle+1, end);
		merge(v, start, middle, end);
	}

}