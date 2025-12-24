import java.io.*;
import java.util.*;





class m{ 
    public static int nCk =0;
    public static int[] resul = new int [100000];

    public  static void somas(int s[], int n){
	int y =0;
	
	for(int i=0; i< n+1; i++){             // calcular quanto espaço o novo 
	    nCk =1;                            // vetor vai precisar
	    for(int k=0; k<2; k++){
		nCk= nCk * (n-k)/(k+1);
	    }
	}



	 resul = new int [nCk+2];
	for(int i=0; i<n; i++){
	    for (int j=i+1; j<n;j++){
		resul[y]= s[i] + s[j];
		y++;
	    }
	}
     
    }





    public static void main(String args[]){




	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt();
	int[] s = new int[n];
	int min = 1000000;
	int dif =0;
	int minimo1=0;
	int minimo2 =10000;
	

		for(int i=0; i<n; i++){
	    s[i]= stdin.nextInt();
	    }

       	somas(s,n);

	
	Arrays.sort(resul);

	/*	for(int i=2; i<nCk+2; i++){
	    System.out.print(resul[i]+ " ");
	    }*/
	//	System.out.println();
	int p= stdin.nextInt();
	for(int i=0; i<p; i++){
	    int pi= stdin.nextInt();
	    int lowmenor=0;
	    int lowmaior=2;
	    int high = nCk+1;
	    while(lowmaior<high){
		int middle= lowmaior +(high -lowmaior)/2;
		if(pi<resul[middle]){high=middle;}
		else if(pi==resul[middle]){lowmaior=middle; break;}
		else{ lowmaior= middle +1;}
		//	System.out.println(".."+lowmenor+ " " + high);

	    }
	    if((pi-resul[lowmaior-1])<(resul[lowmaior]-pi)&& resul[lowmaior-1]!=0){
		lowmaior=lowmaior-1;
	    }
	    else if((pi-resul[lowmaior-1])==(resul[lowmaior]-pi)){
		    lowmenor=lowmaior-1;
		}
		if(lowmenor!=0){System.out.print(resul[lowmenor] + " " +resul[lowmaior]);}
		else{   System.out.print(resul[lowmaior] + " ");}
	    System.out.println();
	}
    }
}
		
	   
			
		
			

