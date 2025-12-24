import java.io.*;
import java.util.*;


class Segmento implements Comparable<Segmento>{
 
    public int li;
    public int ri;
 
    Segmento(int li, int ri){
        
        this.li = li;
        this.ri = ri;
    }

    @Override

    public int compareTo(Segmento seg){

        if(this.li < seg.li){
            return -1;
        }
        else if (this.li > seg.li){
            return 1;
        }
        
        else{
            if(this.ri < seg.ri){
                return 1;
            }
        

            else if (this.ri > seg.ri){
                return -1;
            }
        }

        return 0;

    }
}

class Cobertura{

public static void main(String args[]){

	Scanner input = new Scanner(System.in);

	int m = input.nextInt();
    int n = input.nextInt();
    Segmento[] v = new Segmento[n];
    
    for(int i=0; i<n; i++){
        v[i] = new Segmento(input.nextInt(), input.nextInt());
    }

    Arrays.sort(v);

     System.out.println("ordenado");
     for (int i=0; i<n; i++){
     System.out.println(v[i].li + " " + v[i].ri);
    }
     
    int end =0;
    int i=0;
    int contador =0;
    while (end <m){
          for (i=1; i<n; i++){
               if(v[i].ri > v[i-1].ri){
                  end = v[i].ri;
                  contador++;
               } 
            }
    }        


System.out.println("contador " + contador);


}

}