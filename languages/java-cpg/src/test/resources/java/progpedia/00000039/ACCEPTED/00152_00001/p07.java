
//Este problema foi feito com ajuda do aluno Sérgio Neto

import java.util.*;
 
class p07{
    public static void main(String args []){
        Scanner in = new Scanner(System.in);
         
        int n = in.nextInt();
 
        int s [] = new int [n];
        int somas [] = new int [n*n];
 
        for ( int i=0; i < n; i++)
            s[i] = in.nextInt();
 
        int k=0;
 
        for ( int i=0; i<n; i++ ){
            for ( int j=0; j<n; j++ ){
                if( s[i] != s[j]) {//nao podemos somar os proprios numeros
                somas[k]= s[i]+s[j]; //todas as somas possiveis O(n²)
                k++;
                }
            }
        }
 
        Arrays.sort(somas); // ordenar somas O(n²log n²)
 
        //eliminar os zeros do vector O(n)      
        int [] newsomas = clear(somas);
 
        int p = in.nextInt();
 
        for ( int i=0; i<p; i++ )
            bs( in.nextInt() , newsomas ); // print response here based on the bs
    }
 
    public static void bs(int p, int [] somas){
        int low=0;
        int high=somas.length-1;
        int mid=0;
         
         
        while(high - low > 1){
            mid= low + (high-low)/2;
     
            if( somas[mid] < p)
                low=mid;
            else
                high=mid;
        }
 
         
            if (p - somas[low] == somas[high] - p && somas[low] != p && somas[high]!= p)
                System.out.println(somas[low]+" "+somas[high]);
            else{
                if( p - somas[low] < somas[high] - p)
                    System.out.println(somas[low]);
                else
                    System.out.println(somas[high]);
             
        }
    }
 
 
    public static int [] clear(int [] v){
        List<Integer> l= new ArrayList<Integer>();
        int c=0;
 
        for( int i=0;  i < v.length;  i++ ){
            if (v[i] != 0){
                l.add(v[i]);
                c++;
            }
        }
 
        int array [] = new int [c];
 
        for (int i=0; i < c; i++)
            array[i]=(Integer)l.get(i);
 
        return array;
    }
}
