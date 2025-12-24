//package sigarras;

import java.util.Scanner;

public class Sigarras {

	static final int MAX = 30;
	static int places [] = new int [MAX];
	
	public static void main(String[] args){
		
		Scanner in = new Scanner (System.in);
		int read, counter;
		
		counter = 0;
		read = in.nextInt();
		
		while(read != 0){
				if (isIn(read)) {
					counter = whereIs(read)+1;
				}
				else{
					if (counter == MAX-1){
						places[counter] = read;
						break;
					}
					else{
						places[counter] = read;
						counter++;
					}
				}
		
			read = in.nextInt();
		}
		
		print(places);
	}
	
	public static int whereIs(int i){
		for (int c = 0; c < MAX; c++){
			if (places[c] == i){
				return c;
			}
		}
		return 0;
	}
	
	public static boolean isIn(int i){
		for (int c = 0; c < MAX; c++){
			if (places[c] == i){
				return true;
			}
		}
		return false;
	}
	
	public static void print(int v[]){
		for (int i = 0; i < MAX; i++){
			if (places[i] != 0){
				System.out.println(places[i]);
			}
		}
	}
}
