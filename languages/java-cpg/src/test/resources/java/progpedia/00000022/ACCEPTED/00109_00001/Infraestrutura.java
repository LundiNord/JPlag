/*-------------------------------------------------------------------*\
|  Definicao de grafos com pesos (int)                                |
|     Assume-se que os vertices sao numerados de 1 a |V|.             |
|                                                                     |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012    |
|   Last modified: 2013.01.03                                         |
\--------------------------------------------------------------------*/
import java.io.*;
import java.util.*;


class Infraestrutura{
    public static void main(String args[]){
        Scanner in= new Scanner(System.in);
        int nlocais=in.nextInt();
        int largMin=in.nextInt();
        int largMax=in.nextInt();
        int compMin=in.nextInt();
        int compMax= in.nextInt();
        int altMin=in.nextInt();
        int origem=in.nextInt();
        int destino= in.nextInt();
        //Grafo locaisGrafo = new Grafo(nlocais);//grafo apenas c 2 nos
        
        int contagem=0;
        
        int no1,no2,lMax,cMax,alt;
        
        while((no1=in.nextInt())!=-1){
            no2=in.nextInt();
            lMax=in.nextInt();
            cMax=in.nextInt();
            alt=in.nextInt();
            if(lMax>=largMin && cMax>=compMin && alt>=altMin) contagem++;
        }
        System.out.println(contagem);
    }
}
            
        
