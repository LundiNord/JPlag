import java.util.Scanner;
class Cobertura{
  static int m; //ate onde é que tu podes preencher
  static int n; //numero de vetores
  static int vetores[][];

 /* private static int ordenar(int vetor[]){ //ordena um vetor da forma decrescente ignorando aqueles que deram -1
    int minim=-1,i;
    for (i=0;i<n && vetor[i]==-1 ;++i );
    if(i==n)
		return -1;
	minim=vetor[i++];
   
	 while(i<n){
      if(minim>vetor[i] && vetor[i]!=-1)
        minim=vetor[i];
	++i;
    }
    return minim;
}*/

  private static int goGreedy(int whereIam){
    if (whereIam>=n) {
      return 0;
    }
    //int v[]= new int[n];
	int v[]=new int[2];
	v[1]=0;
    boolean flag=true; //caso nao haver hipotese
    for (int i =0;i<n ;++i) {
      if(vetores[i][0]<=whereIam && vetores[i][1]>whereIam){
        //v[i]=goGreedy(vetores[i][1]);
        if(v[1]<vetores[i][1]){
			v[0]=i;
			v[1]=vetores[i][1];
		}
		//flag=false;
      }
      //else
        //v[i]=-1;
    }
	return (goGreedy(v[1])+1);
   /* if(flag)
      return -1;
    return (ordenar(v)+1);*/
  }

  public static void main(String[] args) {
    Scanner input= new Scanner(System.in);
    m=input.nextInt();
    n=input.nextInt();

    vetores= new int[n][2];
    for (int i=0;i<n ;++i ) {
      vetores[i][0]=input.nextInt();
      vetores[i][1]=input.nextInt();
    }
    System.out.println(goGreedy(0));
  }
}
