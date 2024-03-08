import java.util.ArrayList;

public class Tauler {
    public static ArrayList<Fitxa> negro = new ArrayList<Fitxa>();
    public static ArrayList<Fitxa> blanco = new ArrayList<Fitxa>();

    static Fitxa tauler[][] = new Fitxa[8][8];

    static void printTauler() {
        System.out.println("    a   b   c   d   e   f   g   h");

        System.out.println("  ---------------------------------");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(count + " ");
            System.out.print("| ");
            for (int j = 0; j < 8; j++) {
                if (tauler[i][j] == null) {
                    System.out.print("  | ");
                } else {
                    System.out.print(tauler[i][j] + " | ");
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
            System.out.println("  ---------------------------------");
        }
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println();
    }

    static void startGame() {
        System.out.println("Com jugar al Shatranj:");
        System.out.println(
                "Per al baidaq, escriu \"baidaq\" seguit de la lletra de la columna. Per exemple, \"baidaqA\"");
        System.out.println(
                "Per la resta de peces duplicades, escriu el nom de la peça seguit de si es troba a la part esquerra o dreta \n"
                        +
                        "segons la seva posició original al tauler. Per exemple, \"elefantI f5\"");
        System.out.println(
                "Els baidaq es converteixen automàticament en ministre quan arriben a la darrera fila del tauler.");
        System.out.println(
                "Si en un moviment no pots moure cap peça sense deixar el rei amenaçat, pots intercanviar-la amb una altra peça pròpia.");
        System.out.println(
                "També guanyes si captures el rei contrari, o deixes l'oponent només amb el rei, però tenint més peces.");
        System.out.println(
                "Recorda que moltes partides acaben en taules si cap jugador pot fer un moviment legal.");

        // negro
        new Ruhk(Color.NEGRO, "ruhkI", 0, 0);
        new Faras(Color.NEGRO, "farasI", 1, 0);
        new Elefant(Color.NEGRO, "elefantI", 2, 0);
        new Xa(Color.NEGRO, "xa", 3, 0);
        new Ministre(Color.NEGRO, "ministre", 4, 0);
        new Elefant(Color.NEGRO, "elefantD", 5, 0);
        new Faras(Color.NEGRO, "farasD", 6, 0);
        new Ruhk(Color.NEGRO, "ruhkD", 7, 0);

        new Baidaq(Color.NEGRO, "baidaqA", 0, 1);
        new Baidaq(Color.NEGRO, "baidaqB", 1, 1);
        new Baidaq(Color.NEGRO, "baidaqC", 2, 1);
        new Baidaq(Color.NEGRO, "baidaqD", 3, 1);
        new Baidaq(Color.NEGRO, "baidaqE", 4, 1);
        new Baidaq(Color.NEGRO, "baidaqF", 5, 1);
        new Baidaq(Color.NEGRO, "baidaqG", 6, 1);
        new Baidaq(Color.NEGRO, "baidaqH", 7, 1);

        // blanc
        new Ruhk(Color.BLANCO, "ruhkI", 0, 7);
        new Faras(Color.BLANCO, "farasI", 1, 7);
        new Elefant(Color.BLANCO, "elefantI", 2, 7);
        new Xa(Color.BLANCO, "xa", 3, 7);
        new Ministre(Color.BLANCO, "ministre", 4, 7);
        new Elefant(Color.BLANCO, "elefantD", 5, 7);
        new Faras(Color.BLANCO, "farasD", 6, 7);
        new Ruhk(Color.BLANCO, "ruhkD", 7, 7);

        new Baidaq(Color.BLANCO, "baidaqA", 0, 6);
        new Baidaq(Color.BLANCO, "baidaqB", 1, 6);
        new Baidaq(Color.BLANCO, "baidaqC", 2, 6);
        new Baidaq(Color.BLANCO, "baidaqD", 3, 6);
        new Baidaq(Color.BLANCO, "baidaqE", 4, 6);
        new Baidaq(Color.BLANCO, "baidaqF", 5, 6);
        new Baidaq(Color.BLANCO, "baidaqG", 6, 6);
        new Baidaq(Color.BLANCO, "baidaqH", 7, 6);
    }

    // set Fitxa to provided coordinates
    public static void setFitxa(int x, int y, Fitxa Fitxa) {
        if (Fitxa != null) {
            Fitxa.setX(x);
            Fitxa.setY(y);
        }
        tauler[y][x] = Fitxa;
    }

    // check spot on tauler
    public static Fitxa getFitxa(int x, int y) {
        return tauler[y][x];
    }

    // match String Fitxa from user with Fitxa on tauler
    public static Fitxa getFitxa(String Fitxa, Color color) {

        if (color == Color.BLANCO) {

            for (int i = 0; i < blanco.size(); i++) {
                Fitxa p = blanco.get(i);
                if (p.matchID(Fitxa)) {
                    return p;
                }
            }
        }

        else if (color == Color.NEGRO) {

            for (int i = 0; i < negro.size(); i++) {
                Fitxa p = negro.get(i);
                if (p.matchID(Fitxa)) {
                    return p;
                }
            }
        }

        return null;

    }

    public static boolean camiLliure(int x1, int y1, int x2, int y2) {

        int xDistancia = x2 - x1;
        int yDistancia = y2 - y1;
        int xDir = 0;
        int yDir = 0;
        int size = 0;

        if (xDistancia < 0) {
            xDir = -1;
        } else if (xDistancia > 0) {
            xDir = 1;
        }

        if (yDistancia < 0) {
            yDir = -1;
        } else if (yDistancia > 0) {
            yDir = 1;
        }

        if (xDistancia != 0) {
            size = Math.abs(xDistancia) - 1;
        } else {
            size = Math.abs(yDistancia) - 1;
        }

        for (int i = 0; i < size; i++) {
            x1 += xDir;
            y1 += yDir;

            if (getFitxa(x1, y1) != null) {
                return false;
            }
        }
        return true;

    }

    static int processMove(String move, Color color) {

        String[] splitStr = move.split(" ");
        String Fitxa = splitStr[0];

        Fitxa p = getFitxa(Fitxa, color);
        if (p == null) {
            System.out.println("Fitxa inválida, si us plau, escriviu una fitxa per moure'l.");
            return -1;
        }

        String coordinates = splitStr[1];
        if (coordinates.length() != 2) {
            System.out.println("Casella no vàlid, torna-ho a provar");
            return -1;
        }

        int file = coordinates.charAt(0) - 'a'; // y
        int rank = 7 - (coordinates.charAt(1) - '1'); // x

        if (rank < 0 || rank > 7 || file < 0 || file > 7) {
            System.out.println("Casella no vàlid, torna-ho a provar");
            return -1;
        }

        Fitxa other = getFitxa(file, rank);

        return p.move(file, rank, other);

    }

    public static boolean checkForCheck(Color color) {

        Fitxa xa = getFitxa("xa", color);

        if (color == Color.BLANCO) {
            for (int i = 0; i < negro.size(); i++) {
                Fitxa p = negro.get(i);
                if (p.possibleMov(xa.getX(), xa.getY())) {
                    return true;
                }
            }
        }

        else if (color == Color.NEGRO) {
            for (int i = 0; i < blanco.size(); i++) {
                Fitxa p = blanco.get(i);
                if (p.possibleMov(xa.getX(), xa.getY())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean mate(Color color) {

        if (color == Color.BLANCO) {
            for (int i = 0; i < blanco.size(); i++) {
                Fitxa p = blanco.get(i);
                if (p.potMov()) {
                    return false;
                }
            }
        } else if (color == Color.NEGRO) {
            for (int i = 0; i < negro.size(); i++) {
                Fitxa p = negro.get(i);
                if (p.potMov()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean staleMate(Color color) {

        Fitxa farasI = getFitxa("farasI", color);
        Fitxa farasD = getFitxa("farasD", color);
        Fitxa elefantI = getFitxa("elefantI", color);
        Fitxa elefantD = getFitxa("elefantD", color);

        if (blanco.size() == 2 && negro.size() == 2) {
            if (blanco.contains(elefantI) || blanco.contains(elefantD) || blanco.contains(farasI)
                    || blanco.contains(farasD)) {
                return true;
            }
            if (negro.contains(elefantI) || negro.contains(elefantD) || negro.contains(farasI)
                    || blanco.contains(farasD)) {
                return true;
            }

        }
        if (blanco.size() == 1 && blanco.get(0) instanceof Xa && negro.size() == 1 && negro.get(0) instanceof Xa) {
            return true;
        }

        if (mate(color) == true && checkForCheck(color) == false) {
            return true;
        }

        return false;

    }

}