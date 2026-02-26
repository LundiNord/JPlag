import java.io.*;
import java.util.*;

class Cigarras{
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		
		int x = scn.nextInt();
		if (x != 0){
			Lista ls = new Lista(x);
			x = scn.nextInt();
			while (x != 0){
				ls.add(x);
				x = scn.nextInt();
			}
			ls.print();
		}
	}
	
	static class Lista{
		Node root;
		
		Lista(int x){
			root = new Node(x, null);
		}
		
		public void print(){
			Node p = root;
			while(p != null){
				System.out.println(p.val);
				p = p.next;
			}
		}
		
		public void add(int x){
			Node p = root;
			while (p.val != x && p.next != null)
				p = p.next;
			p.next = null;
			if (p.val != x)
				p.next = new Node(x, null);
		}
		
		static class Node{
			int val;
			Node next;
			
			Node(int v, Node n){
				this.val = v;
				this.next = n;
			}
		}
	}
}