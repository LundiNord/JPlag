import java.io.*;
import java.util.*;


class Par implements Comparable<Par>{
    int pos;
    float money;
    
    Par(int pos , float money){
	this.pos = pos;
	this.money = money;
    }
    
    
    @Override
    public int compareTo(Par s){
	if(this.money == s.money){
	    if(this.pos < s.pos){
		return -1;
	    }
	    else
		return 1;
	}
	else{
	    if(s.money> this.money)
		return 1;
	    else
		return -1;
	}
	
    }


}



public class p10{
    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	Par sapatos[] = new Par[n];
	for(int i=0; i< n;i++){
	    int time = stdin.nextInt();
	    sapatos[i] = new Par(i+1 , stdin.nextFloat()/time);
	}
	Arrays.sort(sapatos);
	for(int i=0;i<n-1;i++){
	    System.out.print(sapatos[i].pos + " ");
	}
	System.out.println(sapatos[(n-1)].pos);
	
       
    }
}
