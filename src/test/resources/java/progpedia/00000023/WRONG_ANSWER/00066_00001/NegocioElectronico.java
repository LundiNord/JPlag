/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication1;

import java.util.Scanner;
import java.util.ArrayList;


public class NegocioElectronico {
    public static void main (String[] args){
        int                     nLojas  =   0;
        int                     nEnd    =   0;
        int                     nIns    =   0;
        int                     nIns2   =   0;
        int                     nVal    =   0;
        int                     nIndex  =   -1;
        int[][]                 arDist  =   null;
        int[]                   arN     =   null;
        ArrayList<Integer>      alQ     =   null;
        Scanner                 myscan  =   new Scanner(System.in);
        
        nLojas      =   myscan.nextInt();
        nEnd        =   myscan.nextInt();
        
        arDist      =   new int[nLojas][nLojas];
        arN         =   new int[nLojas];
        alQ         =   new ArrayList<Integer>();
        
        // Obter dados
        while (1==1)
        {
            nIns    =   myscan.nextInt();
            
            if ( nIns == -1)
            {
                break;
            }
            
            nIns2               =   myscan.nextInt();
            nVal                =   myscan.nextInt();
            
            arDist[nIns-1][nIns2-1] =   nVal;
            arDist[nIns2-1][nIns-1] =   nVal;
        }
        
        // Inicializacao de array
        for ( int i = 0; i < nLojas; i++)
        {
            arN[i]  =   Integer.MAX_VALUE;
            alQ.add(i+1);
            
        }
        
        arN[nEnd-1] =   0;
        
        //Dijsktra
        while ( !alQ.isEmpty() )
        {
            nIndex  =   GetMin(alQ, arN);
            
            System.out.print(nIndex+1 + " ");
            
            // Extract Min
            for ( int i = 0; i < alQ.size(); i++)
            {
                if ( alQ.get(i) == nIndex+1)
                {
                    nVal  =   alQ.remove(i);
                    break;
                }
            }
            
            // Adjacentes
            for ( int i = 0; i < nLojas; i++)
            {
                if ( arDist[nIndex][i] > 0 )
                {
                    arDist[i][nIndex]   =   0;

                    //Relax
                    if ( arN[i] > arN[nIndex] + arDist[nIndex][i] )
                    {
                        arN[i]  =   arN[nIndex] + arDist[nIndex][i];
                    }
                }
            }
            
        }        
    }
   
    public static int GetMin (ArrayList<Integer> alList, int[] arN)
    {
        int nResult = -1;
        int nLojas  = alList.size();
        int nMin    = Integer.MAX_VALUE;
        
        for ( int i = 0; i < alList.size(); i++)
        {
            if ( arN[alList.get(i)-1] < nMin )
            {
                nMin    =   arN[i];
                nResult =   alList.get(i)-1;
                
            }
        }
        
        
        return nResult;
    }
}

/*

Exemplo
Input
9 7 
1 2 32 
1 3 14 
1 4 15 
2 5 35 
3 2 20 
5 3 22 
3 4 25 
5 6 24  
2 6 40 
7 2 40 
7 8 33 
6 7 15 
6 8 25 
9 6 22  
9 8 21 
5 9 17 
5 4 33 
9 4 45 
-1 
 
Output
7 6 8 9 5 2 3 1 
*/
