
//package prob10;
import java.util.*;

public class Prob10 {

    public static void main(String[] args) {
        Scanner le= new Scanner(System.in);
        int n= le.nextInt();
        double [] r= new double[n];
        for(int i=0; i<n; i++){
            int d=le.nextInt();
            int m=le.nextInt();
            r[i]=d/(m+0.00);
        }
        for( int i=0; i<n; i++){
            double min=1000;
            int k=0;
            for(int j=0; j<n; j++)
                if(min>r[j] && r[j]>0.00){
                    min=r[j];
                    k=j;
                }
            if(i!=n-1)
                System.out.print((k+1)+ " ");
            else
                System.out.println(k+1);
            r[k]=0.00;
        }
     
    }
    
}
 
// Em conjunto com João Machado