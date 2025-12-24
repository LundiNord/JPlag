import java.util.*;

public class somas{
	
	static int binarySearch(int [] values, int val,int size) {
		  return binarySearch(values, val, 0, size - 1);
		}

		 
		static int binarySearch(int[] values, int val, int low, int high) {
		  int half=0;
			while ( low <= high ) {
		    half = low + ( high - low ) / 2;
		    if ( val==values[half])
		      return 10000;
		    else if (val < values[half])
		      high = half - 1;
		    else
		      low =  half + 1;
		  }
		  return half;      
		}	
		
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		//quantidade de numeros
		int n=input.nextInt();
		//vetor armazena os numeros
		int s[]=new int[n];

		for (int i=0;i<n;i++)
			s[i]=input.nextInt();
		
		//quantidade perguntas
		int p=input.nextInt();
		//vetor armazena perguntas
		int pg[]=new int[p];

		for (int i=0;i<p;i++)
			pg[i]=input.nextInt();
		
	
		
		
		int h=0;
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				h++;
			}	
		}
		
		int somas[]=new int[h];
		
		h=0;
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				somas[h]=s[i]+s[j];
				h++;
			}	
		}
		
		
		
		Arrays.sort(somas);
		
		
		
	//----------------------------------------------------------------Leitura de valores--------------------------------------------------------------------------------------------------------
		for(int j=0;j<p;j++){
				if(binarySearch(somas,pg[j],h)==10000)
					System.out.println(pg[j]);
				
				else{
					int a=binarySearch(somas,pg[j],h);
					int calculo1=somas[a];
					int calculo2=0;
					int calculo3=0;
					if(a+1<h){
						calculo2=somas[a+1];
					}
					else{
						calculo2=0;
					}
					if(a-1>0){	
						calculo3=somas[a-1];
					}
					else{
						calculo3=0;
					}
					
					
					if(calculo2!=0 && calculo3!=0 && Math.abs(calculo1-pg[j])<Math.abs(calculo2-pg[j]) && Math.abs(calculo1-pg[j])<Math.abs(calculo3-pg[j]))
						System.out.println(calculo1);
					else if(calculo2!=0 && calculo3!=0 && Math.abs(calculo2-pg[j])<Math.abs(calculo1-pg[j]) && Math.abs(calculo2-pg[j])<Math.abs(calculo3-pg[j]))
						System.out.println(calculo2);
					else if(calculo2!=0 && calculo3!=0 && Math.abs(calculo3-pg[j])<Math.abs(calculo1-pg[j]) && Math.abs(calculo3-pg[j])<Math.abs(calculo2-pg[j]))
						System.out.println(calculo3);
					else if(calculo2==0 && Math.abs(calculo1-pg[j])<Math.abs(calculo3-pg[j]))
						System.out.println(calculo1);
					else if(calculo3==0 && Math.abs(calculo1-pg[j])<Math.abs(calculo2-pg[j]))
						System.out.println(calculo1);
					else if(calculo3==0 && Math.abs(calculo2-pg[j])<Math.abs(calculo1-pg[j]))
						System.out.println(calculo2);
					else if(calculo2==0 &&  Math.abs(calculo3-pg[j])<Math.abs(calculo1-pg[j]))
						System.out.println(calculo3);
					
					
					else if(Math.abs(calculo1-pg[j])==Math.abs(calculo2-pg[j]) && calculo1!=calculo2){
						if(calculo1<calculo2)
							System.out.println(calculo1+" "+calculo2);
						else
							System.out.println(calculo2+" "+calculo1);
					}
					else if(Math.abs(calculo1-pg[j])==Math.abs(calculo2-pg[j]) && calculo1==calculo2){
						int b=somas[a+2];
						if(Math.abs(calculo1-pg[j])==Math.abs(b-pg[j]) && calculo1==b)
							System.out.println(calculo1);
						else if(Math.abs(calculo1-pg[j])==Math.abs(b-pg[j]) && calculo1!=b){
							if(calculo1<somas[a+2])
								System.out.println(calculo1+" "+b);
							else
								System.out.println(b+" "+calculo1);
						}
					}
					
					
					
					else if(Math.abs(calculo1-pg[j])==Math.abs(calculo3-pg[j]) && calculo1!=calculo3){
						if(calculo1<calculo3)
							System.out.println(calculo1+" "+calculo3);
						else
							System.out.println(calculo3+" "+calculo1);
					}
					if(Math.abs(calculo1-pg[j])==Math.abs(calculo3-pg[j]) && calculo1==calculo3){
						if(Math.abs(calculo1-pg[j])==Math.abs(somas[a-2]-pg[j]) && calculo1==somas[a-2]){
							System.out.println(calculo1);
						}
						else if(Math.abs(calculo1-pg[j])==Math.abs(somas[a-2]-pg[j]) && calculo1!=somas[a-2]){
							if(calculo1<somas[a-2])
								System.out.println(calculo1+" "+somas[a-2]);
							else
								System.out.println(somas[a-2]+" "+calculo1);
						}		
					}
					
					
					
					
					else if(Math.abs(calculo2-pg[j])==Math.abs(calculo3-pg[j]) && calculo2!=calculo3){
						if(calculo2<calculo3)
							System.out.println(calculo2+" "+calculo3);
						else
							System.out.println(calculo3+" "+calculo2);
					}
					else if(Math.abs(calculo2-pg[j])==Math.abs(calculo3-pg[j]) && calculo2==calculo3)
						System.out.println(calculo2);
				
					
					
					
					
					
					
				}
		}//fim do for

		




	}
}//Fim da classe principal




