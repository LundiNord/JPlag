import java.util.*;
import java.lang.Float;

//Feito com Fábio Queirós e Marcos Mirancos

class sap implements Comparable<sap> {
    public int dias, multa, index;
    public float racio;
    sap(int d, int m, int i){
        this.dias=d;
        this.multa=m;
        this.index=i;
        this.racio=(float)(this.multa/this.dias);
    }
   
    @Override
    public int compareTo(sap fabio) {
        float a=this.racio;
        float b=fabio.racio;
        if(Float.compare(a,b) < 0){
            return 1;
        }
        /*else if(Float.compare(a,b) == 0){
                if(this.index<fabio.index){
                        return -1;
                }
                else{
                        return 1;
                }
        }*/
        else{ return -1;}
           
    }
 
}
public class prob10{
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        sap marcos[]=new sap[n];
        sap tmp1[]=new sap[1];
        for(int i=0;i<n;i++){
            marcos[i]=new sap(input.nextInt(), input.nextInt(), i);
        }
        Arrays.sort(marcos);
        //Verificar os index

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(marcos[i].racio==marcos[j].racio){
                    if(marcos[i].index>marcos[j].index){
                        tmp1[0]=marcos[j];
                        marcos[j]=marcos[i];
                        marcos[i]=tmp1[0];
                    }
                }
            }
        }


        for(int i=0;i<n-1;i++){
            System.out.print(marcos[i].index+1 + " ");
        }
        System.out.println(marcos[n-1].index+1);

        //teste
        /*
        System.out.println();
        for(int i=0;i<n;i++){
        System.out.println(marcos[i].racio);}
    */
    }
}
