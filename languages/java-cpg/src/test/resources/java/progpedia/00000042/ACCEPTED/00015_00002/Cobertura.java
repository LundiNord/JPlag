import java.util.*;

class Activity implements Comparable<Activity>{

    public int a;
    public int b;

    Activity(int a, int b){
	this.a = a;
	this.b = b;
    }

    //comparar ordem ascendente de a
    
    @Override
    public int compareTo(Activity act){

	if(act.a > this.a){
	    return -1;
	}else if(act.a < this.a){
	    return 1;
	}else{
	    if(act.b >= this.b){
		return 1;
	    }else{
		return -1;
	    }
	    
	}
	    
    }


}


class Cobertura{

    public static void main(String args[]){


	Scanner input = new Scanner(System.in);
	int M = input.nextInt();
	int N = input.nextInt();
	Activity[] act = new Activity[N];
	for(int i=0; i<N; i++){
	    act[i] = new Activity(input.nextInt(), input.nextInt());
	}
	Arrays.sort(act);

	int end=0;
	int out=0;
	int seg=0;
	int r=-1;
	int inc = 0;
	while(end < M){

	    //selecionar segmento de act
	    //onde o primeiro elemento a <= end
	    loop:for(Activity a: act){
		if(a.a <= end && a.b > r){
		    r = a.b;
		    seg = inc;
		}else if(a.a > end){
		    inc = 0;
		    r=-1;
		    break loop;
		}
		inc++;
	    }
	    //actualizar end para sitio onde termina o segmento escolhido
	    // end = b;
	    end = act[seg].b;
	    out++;

	}

	System.out.println(out);
	

    }


}
