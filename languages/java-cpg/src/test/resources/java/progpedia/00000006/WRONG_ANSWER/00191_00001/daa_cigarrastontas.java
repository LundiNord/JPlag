
// topas2006_C

import java.util.Scanner;

public class daa_cigarrastontas {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int loc[] = new int[30];
        int cursor = 0;
        int c = in.nextInt();
        
        while (c != 0) {
            
            for (int i = 0; i < loc.length; i++)
                if (loc[i] == c)
                    cursor = i;
            
            loc[cursor] = c;
            cursor++;
            
            c = in.nextInt();
        }
        
        for (int j = 0; j < loc.length; j++)
            if (loc[j] != 0)
                System.out.println(loc[j]);        
    }
}