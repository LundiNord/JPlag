import java.util.Scanner;

class PiramDinamic{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int n=input.nextInt();  //numero de numeros no nivel mais baixo da piramide
    int d = input.nextInt(); // numero d epedras em falta
    //int pedrasMas[][] = new int[d][2];

    //array que seera a area de trabalho
    int piramide[][] = new int[n][n]; // c e p


    //iniciar a ultima linha a 0
    for(int j = 0; j < n ; ++j){
      int i = n-1;
      piramide[i][j]=1;
    }


    for (int i =0;i<d ;++i ) //guardar as pedras danificadas nas suas posições
      piramide[(n-input.nextInt())][(input.nextInt()-1)]=-1; //conversao do sistema de coordenadas no input para o meu

    //comeca a construcao dos restantes
    for (int i = n-2; i>=0; --i){
      for(int j = 0; j <(i+1); ++j){
        if(piramide[i][j]!=-1){ //se  apedra estiver danificada nada faz
          //para o caso de duas pedras abaixo uma ao lado da outra estarem danificadas a d ecima tbm fica ja que nao a caminho que  apossoa utilizar
          if (piramide[i+1][j]==-1 && piramide[i+1][j+1]==-1){
            piramide[i][j]=-1;
            continue;
          }

          //caso de ter dois caminhos possiveis
          if(piramide[i+1][j]!=-1 && piramide[i+1][j+1]!=-1){
            int som=0;
            som+=(piramide[i+1][j]);
            som+=(piramide[i+1][j+1]);
            piramide[i][j]=som;
            continue;
          }
          //só temos uma opçao
          int som=0;
          if (piramide[i+1][j]!=-1) som+=(piramide[i+1][j]);
          if (piramide[i+1][j+1]!=-1) som+=(piramide[i+1][j+1]);
          piramide[i][j]=som;
        }
      }
    }

    /*impessão*/
      /*    for (int i = 0; i<n; ++i){
            for(int j = 0; j <(i+1); ++j){
              System.out.print(piramide[i][j]+" ");
            }
            System.out.println();
          }*/

    if(piramide[0][0]!=-1)
      System.out.println(piramide[0][0]);
    else
      System.out.println("0");
  }
}
