import java.util.*;



class DAA7{
    public static int somas[];
    public static int pares[];
    static void bsearch(int v[], int low, int high, int key){
	int middle=0;
	while(low < high){
	    middle = low+(high-low)/2;
	    if(key<=v[middle])
		high=middle;
	    else
		low=middle+1;
	}
	if((low-1)>0 && (low<v.length)){
	    if(v[low]==key)                                //Encontra numero igual
		System.out.println(v[low]); 
	    else if(key-v[low-1]==v[low]-key)   //Esquerda igual a direita
		System.out.println(v[low-1]+" "+v[low]) ;
	    else if((key-v[low-1])<(v[low]-key))  //Esquerda mais proximo
		System.out.println(v[low-1]);
	    else                                           //Direita mais proximo
		System.out.println(v[low]);
	}
	else if(low-1<=0)
	    System.out.println(v[0]);
	else
	    System.out.println(v[low-1]);
  
	
    }
		    
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);

	int s = in.nextInt();
	int v[]= new int [s];
	for(int i=0; i<s; i++)
	    v[i]=in.nextInt();

	int p = in.nextInt();
	int j[]= new int[p];
	for(int k=0; k<p; k++)
	    j[k]=in.nextInt();	

	int cal=s*(s-1)/2;
	somas = new int[cal];

	int conta=0;
	for(int i=0; i<=s-1; i++)
	    for(int l=i+1; l<=s-1; l++){
		somas[conta]=v[i]+v[l];
		  conta++;
	    }
	Arrays.sort(somas);
	/*for(int h=0; h<cal; h++)
	  System.out.println(somas[h]);*/
	
	for(int x=0; x<p; x++){
	    bsearch(somas,0,cal,j[x]);
	}
    }
}
