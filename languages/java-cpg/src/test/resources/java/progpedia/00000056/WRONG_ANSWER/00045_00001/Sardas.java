import java.util.*;
import java.io.*;

class SNo{
	float x,y;
	double dist;
	boolean visitado;

	SNo(float x,float y){
		this.x = x;
		this.y = y;
	}

	double paraDist(SNo t){
		return Math.sqrt((x - t.x) * (x - t.x) + (y - t.y) * (y - t.y));
	}
}
public class Sardas{

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);

		LinkedList<SNo> q = new LinkedList<SNo>();
		SNo no;
		int n = input.nextInt();
		SNo[] v = new SNo[n];

		for(int i=0;i<n;i++){
			v[i] = new SNo(input.nextFloat(),input.nextFloat());
			q.add(v[i]);
		}	
		for(int i=0;i<n;i++)
			v[i].dist = 1000;

		v[0].dist = 0;
		while(q.size()>0){
			no = q.removeFirst();
			no.visitado = true;
			for(int i=0;i<n;i++){
				double d = no.paraDist(v[i]);
				if((v[i].visitado == false) && d<v[i].dist){
					v[i].dist = d;
				}
			}

		}
		double sum=0;
		for(int i=0;i<n;i++)
			sum = sum + v[i].dist;

		System.out.printf("%.2f\n",sum);
	}

}