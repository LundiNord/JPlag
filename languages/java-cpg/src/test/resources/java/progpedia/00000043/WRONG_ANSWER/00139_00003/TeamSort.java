import java.io.*;
import java.util.*;

class Team implements Comparable<Team>{
    public int time;
    public int fine;
    public int cost;
    public int order;
    public int dif;
    Team(int t,int f, int o){
	time = t;
	fine = f;
    cost = t*f;
    order = o;
    if( f > t ) dif = f - t;
    else if ( f < t ) dif = t - f;
    else dif = 0;
    }
    @Override
    public int compareTo(Team t2){
    if (dif < t2.dif) return +1;
    if (dif > t2.dif) return -1;
  	if (time > t2.time) return +1;
    if (time < t2.time) return -1;
	if (cost > t2.cost) return +1;
	if (cost < t2.cost) return -1;

	return order - t2.order;
    }
}

public class TeamSort{
    
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	Team teams[] = new Team[n];
	for(int i = 0; i < n; i++){
	    teams[i] = new Team(stdin.nextInt(),stdin.nextInt(),i+1);
	}
	
	Arrays.sort(teams);
	for (int i = 0; i<n; i++)
	    System.out.println(teams[i].order);
    }
    
}
