/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication1;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author x00995
 */
public class Sociologia {
    public static int nCountElem   =   0;
    
    public static void main(String[] args)
    {
        Scanner myscan      =   new Scanner(System.in);
        int     nCases      =   0;
        int                nNumAlumni  =   0;
        int                nIdAl       =   0;
        int                nIdAl2      =   0;
        int         nNumFriends =   0;
        int         nNumGroup   =   0;
        int         nCountElem  =   0;
        ArrayList<Integer> alOnAGroup   =   null;
        int[]   arVisited   =   null;
        int[][] arGroups    =   null;
        int[][] arTransp    =   null;
        
        alOnAGroup  =   new ArrayList<Integer>();
        
        nCases  =   myscan.nextInt();
        
        for (int i = 0; i < nCases; i++) {
            
            nNumAlumni  =   myscan.nextInt();
            
            arGroups    =   new int[nNumAlumni][nNumAlumni];
            arTransp    =   new int[nNumAlumni][nNumAlumni];
            arVisited   =   new int[nNumAlumni];
            
            for (int j = 0; j < nNumAlumni; j++) {
                nIdAl       =   myscan.nextInt();
                
                nNumFriends =   myscan.nextInt();
                
                for (int k = 0; k < nNumFriends; k++) {                    
                    
                    nIdAl2                  =   myscan.nextInt();
                    arGroups[nIdAl-1][nIdAl2-1] =   1;
                    arTransp[nIdAl2-1][nIdAl-1] =   1;
                }
            }    
            
            for (int l = 0; l < nNumAlumni; l++) {
                arVisited[l]    =   0;
            }
        
            alOnAGroup  =   new ArrayList<Integer>();
            
            CC(arGroups, arVisited);
        
            CC_Transp(nNumGroup, arTransp, arVisited, alOnAGroup, i);
        }
        
        
        
    }
    
    public static void CC(int[][] arGroups,int[] arVisited)
    {
        for (int i = 0;  i < arVisited.length; i++) {
            if ( arVisited[i] == 0) {
                
                DFS(arGroups, arVisited, i);
                
            }

        }
    }
    
    public static void CC_Transp(int nNumGroup, int[][] arGroups,int[] arVisited, ArrayList<Integer> alOnAGroup, int nCase)
    {
        int nDummy  =   0;
        
        for (int i = 0;  i < arVisited.length; i++) {
            if ( arVisited[i] < 2) {
                nCountElem  =   0;
                
                DFS_Transp(arGroups, arVisited, alOnAGroup, i);
                
                if(nCountElem >= 4)
                {
                    nNumGroup++;
                }
                else
                {
                    for (int j = 1; j <= nCountElem && !alOnAGroup.isEmpty(); j++) {
                        nDummy  =   alOnAGroup.remove(alOnAGroup.size()-1);
                        
                    }
                }
            }

        }
        
        System.out.println("Caso #" + (nCase + 1));
        System.out.println(nNumGroup + " " + (arVisited.length - alOnAGroup.size()));
    }
    
    public static void DFS( int[][] arGroups, int[] arVis,  int n)
    {
        arVis[n]++;                            
        
        for (int i = 0; i < arGroups[n].length; i++) {
            if(arVis[i] == 0 && arGroups[n][i] != 0)
               DFS(arGroups, arVis, i);
        }
    }
    
    public static  void DFS_Transp(int[][] arGroups, int[] arVis, ArrayList<Integer> alOnAGroup, int n)
    {
        arVis[n]++;               
        
        if(arVis[n] > 1)
        {
            alOnAGroup.add(n);
            nCountElem++;
        }
        
        for (int i = 0; i < arGroups[n].length; i++) {
            if(arVis[i] < 2 && arGroups[n][i] != 0)
               DFS_Transp(arGroups, arVis, alOnAGroup, i);
        }
        
        
    }
}

/*
Input
4 
4 
1 3 2 4 3 
4 0 
2 2 1 3 
3 2 2 1 
6 
1 2 3 5 
2 2 3 4 
4 1 2 
3 2 2 1 
6 1 5 
5 2 6 1 
8 
1 4 6 2 4 5 
3 1 2 
2 2 3 4 
4 1 5 
6 0 
5 3 4 8 7 
7 1 5 
8 2 5 3 
10 
1 4 6 2 4 5 
3 2 2 1 
9 0 
2 2 3 4 
4 2 5 9 
6 1 1 
5 3 4 8 7 
7 1 5 
8 1 5 
10 1 9 


Output
Caso #1
0 4
Caso #2
1 0
Caso #3
1 2
Caso #4
2 2
*/