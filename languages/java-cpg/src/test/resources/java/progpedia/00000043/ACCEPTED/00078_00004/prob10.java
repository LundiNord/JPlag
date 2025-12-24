import java.io.*;
import java.util.*;

class sapateiro implements Comparable<sapateiro> {
    
    public float dias, multa;
    public int index;
    public float racio;
    
    sapateiro(int d, int m, int i){
    this.dias=d;
    this.multa=m;
    this.index=i;
    this.racio=this.multa/this.dias;
    }
   
    @Override
    public int compareTo(sapateiro sap) {
        float a=this.racio;
        float b=sap.racio;
        if (Float.compare(a,b) < 0){
            return 1;
        }
        else{ 
            return -1;}
        }
    }

public class prob10{
    
    public static void main(String args[]){
        
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        
        sapateiro tmp[]=new sapateiro[n];
        sapateiro tmpyy[]=new sapateiro[1];
        
        for(int i=0;i<n;i++){
            tmp[i]=new sapateiro(input.nextInt(), input.nextInt(), i);
        }
        
        Arrays.sort(tmp);
        

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(Float.compare(tmp[i].racio,tmp[j].racio)==0){
                    if(tmp[i].index>tmp[j].index){
                        tmpyy[0]=tmp[j];
                        tmp[j]=tmp[i];
                        tmp[i]=tmpyy[0];
                    }
                }
            }
        }


        for(int i=0;i<n-1;i++){
            System.out.print(tmp[i].index+1 + " ");
        }
        System.out.println(tmp[n-1].index+1);
    }
}



// Programa realizado com a ajuda do Fabio Queiros e Fabio Faria