import java.util.Scanner;

public class Xatranj {
    public static void main(String[] args) {
        Scanner movimiento = new Scanner(System.in);

        while (true) {
            Tauler.startGame();

            int turns = 0;
            Color color;

            while (true) {
                Tauler.printTauler();
                // comprovar escac
                if (turns % 2 == 0) {
                    color = Color.BLANCO;
                } else {
                    color = Color.NEGRO;
                }

                if (Tauler.staleMate(color) == true) {
                    System.out.println("final del joc, empat");
                    break;
                }
                if (Tauler.checkForCheck(color) == true) {
                    if (Tauler.mate(color) == true) {

                        System.out.printf("Escac i mat, guanya %s \n", color == Color.BLANCO ? "Negre" : "Blanc");
                        break;
                    }
                    System.out.printf("%s està en Escac! \n", color == Color.BLANCO ? "Blanc" : "Negre");
                }

                // elecció del moviment
                System.out.printf("Torn de %s \n", color == Color.BLANCO ? "Blanc" : "Negre");

                String move = movimiento.nextLine();
                // processar moviment
                if (Tauler.processMove(move, color) == 0) {
                    turns++;
                } else {
                    System.out.println("moviment il·legal");
                }

            }
            System.out.println("Vols jugar de nou? s/n");
            if (movimiento.next().equals("s")) {
                continue;
            } else
                System.exit(0);
        }
    }

}
