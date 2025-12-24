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
 
class prob9{
 
public static void main(String args[]){
 
    Scanner input = new Scanner(System.in);
 
    int m = input.nextInt();
    int n = input.nextInt();
    Segmento[] v = new Segmento[n];
   
    for(int i=0; i<n; i++){
        v[i] = new Segmento(input.nextInt(), input.nextInt());
    }
 
    Arrays.sort(v);
     
    int end =0;
    int x=-1;
    int contador =0;
    int pos=0;
    while (end <m){
          for (int i=0; i<n; i++){
               if(v[i].li <= end && v[i].ri >x){
                 x=v[i].ri;
                 pos=i;
               }
               else if(v[i].li > end){
                 x=-1;
                 break;
               }
 
           }
           end = v[pos].ri;
           contador++;
    }
 
 
System.out.println(contador);
 
 
}
 
}