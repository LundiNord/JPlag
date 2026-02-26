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
    avg = f*100 /t;
    if( f > t ) dif = f - t;
    else if ( f < t ) dif = t - f;
    else dif = 0;
    }
    @Override
    public int compareTo(Team t2){
    if (avg < t2.avg) return +1;
    if (avg > t2.avg) return -1;
	if (cost < t2.cost) return +1;
	if (cost > t2.cost) return -1;
  	if (time > t2.time) return +1;
    if (time < t2.time) return -1;
   


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
	    System.out.print(teams[0].order);    
	for (int j = 1;j<n; j++)
        System.out.print(" " + teams[j].order);
	    //System.out.println(" " + teams[j].order +"(time:" + teams[j].time + ") (fine:" + teams[j].fine + ") (dif:" + teams[j].dif + ") avg:" +teams[j].avg + ")");
    System.out.println();
    }
}
