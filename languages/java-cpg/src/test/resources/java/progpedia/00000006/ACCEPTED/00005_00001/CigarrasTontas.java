/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Pedro Coelho
 */
public class CigarrasTontas
    {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
        {
        Stack<Integer> myStack = new Stack<Integer>();
        Scanner myScan = new Scanner(System.in);
        int x;
        while (true)
            {
            x = myScan.nextInt();
            if (x == 0)
                {
                break;
                }
            if (myStack.contains(x))
                {
                while (myStack.pop() != x)
                    {
                    }
                }
            myStack.push(x);
            }
        for (int i = 0, j = myStack.size(); i < j; i++)
            {
            System.out.println(myStack.get(i));
            }
        }
    }
