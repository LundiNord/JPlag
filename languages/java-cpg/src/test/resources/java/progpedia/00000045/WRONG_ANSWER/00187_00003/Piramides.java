import java.util.Scanner;

class S {

    Scanner scan;
    int n, npaths;
    int[][] matrix;
    
    
    public S() {
        scan = new Scanner(System.in);
        this.read();
        System.out.println(this.paths(this.n-1, 0));
    }
    
    private void erase(int x, int y){
        this.matrix[x-1][y-1] = -1;
    }
    
    private int paths(int x, int y){
        if (this.matrix[x][y] > 0){
            return this.matrix[x][y];
        }
        if (x == 0 && this.matrix[x][y] != -1){
            return 1;
        }
        if (this.matrix[x][y] == -1){
            return 0;
        }
        int val = this.paths(x-1,y) + this.paths(x-1,y+1);
        this.matrix[x][y] = val;
        return val;
    }
    
    private void read(){
        this.n = this.scan.nextInt();
        this.matrix = new int[this.n][this.n];
        int e = this.scan.nextInt();
        for (int i=1; i<=e; i++){
            this.erase(this.scan.nextInt(), this.scan.nextInt());
        }
    }
}

class Piramides {
  public static void main(String args[]) {
        S s = new S();
    }  
}
