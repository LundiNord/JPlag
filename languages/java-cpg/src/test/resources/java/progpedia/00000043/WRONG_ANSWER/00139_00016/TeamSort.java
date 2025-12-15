import java.io.*;
import java.util.*;

class Team implements Comparable<Team>{
    public int time;
    public int fine;
    public int cost;
    public int order;
    public int dif;
    public double avg;
    Team(int t,int f, int o){
	time = t;
	fine = f;
    cost = t*f;
    order = o;
    avg = f*100000 /t;
    if( f > t ) dif = f - t;
    else if ( f < t ) dif = t - f;
    else dif = 0;
    }
    @Override
    public int compareTo(Team t2){
    if (avg < t2.avg) return +1;
    if (avg > t2.avg) return -1;

    if (order > t2.order) return +1;
	if (order < t2.order) return -1;

	return +1;
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
	   System.out.print(teams[0].order);    
	for (int j = 1;j<n; j++)
       System.out.print(" " + teams[j].order);
	   //System.out.println(" " + teams[j].order + "(cost:" + teams[j].cost + ") (dif:" + teams[j].dif + ") avg:" +teams[j].avg + ")");
    System.out.println();
    }
}
