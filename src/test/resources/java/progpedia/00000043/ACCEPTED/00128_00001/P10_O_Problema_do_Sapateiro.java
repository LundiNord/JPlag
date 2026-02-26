
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Herlander Silva
 */

class Shoe implements Comparable<Shoe> {

    int numOrder;
    int daysToRepair;
    int delayFine;
    double racio;

    public Shoe(int numOrder, int daysToRepair, int delayFine) {
        this.numOrder = numOrder;
        this.daysToRepair = daysToRepair;
        this.delayFine = delayFine;
        this.racio = (double)daysToRepair / delayFine;
    }

    public int getNumOrder() {
        return this.numOrder;
    }

    @Override
    public int compareTo(Shoe n) {
        return this.racio - n.racio == 0 ? 0 : this.racio - n.racio > 0 ? 1 : -1;
    }
}
public class P10_O_Problema_do_Sapateiro {

    static Shoe[] shoes;
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner stdin = new Scanner(System.in);
//        @SuppressWarnings("resource")
//        Scanner stdin = new Scanner(new File("Problemas/P10_O_Problema_do_Sapateiro.in"));
        int dim = stdin.nextInt();

        shoes = new Shoe[dim];
        int idx = 0;

        while (idx <= dim - 1) {
            shoes[idx] = new Shoe(idx + 1, stdin.nextInt(), stdin.nextInt());
            idx++;
        }
        
        Arrays.sort(shoes);
        
        Shoe shoe;
        idx = 0;
        while (idx < dim - 1) {
            shoe = shoes[idx];
            System.out.print(shoe.numOrder + " ");
            idx++;
        }
//        for(Shoe shoe: shoes) {
//            System.out.print(shoe.numOrder + " ");
//        }
        shoe = shoes[idx];
        System.out.println(shoe.numOrder);
    }
    
}
