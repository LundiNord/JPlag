//package com.cigarrastontas;

import java.util.*;

public class CigarrasTontas {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		LinkedList<Integer> a = new LinkedList<Integer>();
		int n;
		int index = 0;
		n = in.nextInt();

		while (n != 0) {
			a.add(n);
			n = in.nextInt();
		}
		int[] b = new int[30];
		for (int i = 0; i < a.size(); i++) {
			for (int j = i; j < a.size(); j++) {
				if (a.get(i).equals(a.get(j))) {
					i = j;
				}
				b[index] = a.get(i);
			}
			index++;
		}

		for (int i = 0; i < index; i++)
			System.out.println(b[i]);
	}
}