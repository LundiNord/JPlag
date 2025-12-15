import java.util.*;

class cigarras{
    
    static final int NLMAX = 30;
    static final int LMAX = 10000;
    static final int FINAL = 0;	
    
    public static int verifica(int  loc, int [] v){
	int i;
	for (i= 0;  i<NLMAX && v[i]!=loc  ; i++){
	}
	if(i<30)		
	    return i;
	else
	    return 31;
    }
    

    
    public static void main(String[] args){
	
	Scanner in = new Scanner (System.in);
	int localat;
	int contador=0;
	int [] caminho = new int[NLMAX];
	
	localat = in.nextInt();

	while(localat != 0){
	    int x = verifica (localat,caminho);
	    
	    if(x<30){

		caminho[x]=localat;
		contador = x;
	    }
	    else
		caminho[contador] = localat; 
	    
	    contador++;
	    localat = in.nextInt();
	}

	for(int i = 0; i< contador; i++){

	    System.out.println(caminho[i]);
	}
    }
    
    
    
}
