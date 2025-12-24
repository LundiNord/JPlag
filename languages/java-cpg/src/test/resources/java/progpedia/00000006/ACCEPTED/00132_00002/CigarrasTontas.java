

import java.util.ArrayList;
import java.util.Scanner;

public class CigarrasTontas {
    public static void main(String[] args) 
    {
        final int           FINAL   =   0;
        int                 nIns    =   -1;
        Scanner             myScan  =   new Scanner(System.in);        
        ArrayList<Integer>  alPath  =   new ArrayList<Integer>();        
        
        while (nIns != FINAL)
        {
            nIns    =   myScan.nextInt();
        
            if(alPath.indexOf(nIns) != -1)                                      // Remove Ex: 1,2,3,4,5,6,2,10 fica 1,2,10
            {
                alPath  =   RemovePaths(alPath, alPath.indexOf(nIns), alPath.size());
            }
            
            if(nIns != FINAL)
                alPath.add(nIns);
        }
        
        for (int nVal : alPath)
        {
            System.out.println(nVal);
        }
        
    }
    
    public static ArrayList<Integer> RemovePaths (ArrayList<Integer> alPath, int nPathBegin, int nPathEnd)
    {
        while (nPathBegin < nPathEnd)
        {
            nPathEnd--;
            alPath.remove(nPathEnd);
        }
        
        return alPath;
    }
}

/*
    Input 1
    1600
    15
    2315
    15
    1315
    0
 
    Output 1
    1600
    15  
    1315
 
 
    Input 2
    1513
    3171
    178
    1316
    1600
    1513
    1774
    178
    3171
    672
    1315
    0
 
    Output2
    1513
    1774
    178
    3171
    672
    1315
 */