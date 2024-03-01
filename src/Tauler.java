import java.util.ArrayList;

public class Tauler {
    public static ArrayList<Fitxa> black = new ArrayList<Fitxa>();
    public static ArrayList<Fitxa> white = new ArrayList<Fitxa>();

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
        System.out.println("Per al baidaq, escriu \"baidaq\" seguit de la lletra de la columna. Per exemple, \"baidaqA\"");
        System.out.println("Per la resta de peces duplicades, escriu el nom de la peça seguit de si es troba a la part esquerra o dreta \n" +
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
        new Ruhk(Color.BLACK, "ruhkI", 0, 0);
        new Faras(Color.BLACK, "farasI", 1, 0);
        new Elefant(Color.BLACK, "elefantI", 2, 0);
        new Xa(Color.BLACK, "xa", 3, 0);
        new Ministre(Color.BLACK, "ministre", 4, 0);
        new Elefant(Color.BLACK, "elefantD", 5, 0);
        new Faras(Color.BLACK, "farasD", 6, 0);
        new Ruhk(Color.BLACK, "ruhkD", 7, 0);

        new Baidaq(Color.BLACK, "baidaqA", 0, 1);
        new Baidaq(Color.BLACK, "baidaqB", 1, 1);
        new Baidaq(Color.BLACK, "baidaqC", 2, 1);
        new Baidaq(Color.BLACK, "baidaqD", 3, 1);
        new Baidaq(Color.BLACK, "baidaqE", 4, 1);
        new Baidaq(Color.BLACK, "baidaqF", 5, 1);
        new Baidaq(Color.BLACK, "baidaqG", 6, 1);
        new Baidaq(Color.BLACK, "baidaqH", 7, 1);

        // blanc
        new Ruhk(Color.WHITE, "ruhkI", 0, 7);
        new Faras(Color.WHITE, "farasI", 1, 7);
        new Elefant(Color.WHITE, "elefantI", 2, 7);
        new Xa(Color.WHITE, "xa", 3, 7);
        new Ministre(Color.WHITE, "ministre", 4, 7);
        new Elefant(Color.WHITE, "elefantD", 5, 7);
        new Faras(Color.WHITE, "farasD", 6, 7);
        new Ruhk(Color.WHITE, "ruhkD", 7, 7);

        new Baidaq(Color.WHITE, "baidaqA", 0, 6);
        new Baidaq(Color.WHITE, "baidaqB", 1, 6);
        new Baidaq(Color.WHITE, "baidaqC", 2, 6);
        new Baidaq(Color.WHITE, "baidaqD", 3, 6);
        new Baidaq(Color.WHITE, "baidaqE", 4, 6);
        new Baidaq(Color.WHITE, "baidaqF", 5, 6);
        new Baidaq(Color.WHITE, "baidaqG", 6, 6);
        new Baidaq(Color.WHITE, "baidaqH", 7, 6);
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

        if (color == Color.WHITE) {

            for (int i = 0; i < white.size(); i++) {
                Fitxa p = white.get(i);
                if (p.matchID(Fitxa)) {
                    return p;
                }
            }
        }

        else if (color == Color.BLACK) {

            for (int i = 0; i < black.size(); i++) {
                Fitxa p = black.get(i);
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
        // change on x and y

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

        // Fitxa selected to move
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

        // Fitxa at destination
        Fitxa other = getFitxa(file, rank);

        return p.move(file, rank, other);

    }

    public static boolean checkForCheck(Color color) {

        Fitxa xa = getFitxa("xa", color);

        if (color == Color.WHITE) {
            for (int i = 0; i < black.size(); i++) {
                Fitxa p = black.get(i);
                if (p.possibleMov(xa.getX(), xa.getY())) {
                    return true;
                }
            }
        }

        else if (color == Color.BLACK) {
            for (int i = 0; i < white.size(); i++) {
                Fitxa p = white.get(i);
                if (p.possibleMov(xa.getX(), xa.getY())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean mate(Color color) {

        if (color == Color.WHITE) {
            for (int i = 0; i < white.size(); i++) {
                Fitxa p = white.get(i);
                if (p.potMov()) {
                    return false;
                }
            }
        } else if (color == Color.BLACK) {
            for (int i = 0; i < black.size(); i++) {
                Fitxa p = black.get(i);
                if (p.potMov()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean staleMate(Color color) {

        // insufficient material stalemate
        Fitxa farasI = getFitxa("farasI", color);
        Fitxa farasD = getFitxa("farasD", color);
        Fitxa elefantI = getFitxa("elefantI", color);
        Fitxa elefantD = getFitxa("elefantD", color);

        if (white.size() == 2 && black.size() == 2) {
            if (white.contains(elefantI) || white.contains(elefantD) || white.contains(farasI)
                    || white.contains(farasD)) {
                return true;
            }
            if (black.contains(elefantI) || black.contains(elefantD) || black.contains(farasI)
                    || white.contains(farasD)) {
                return true;
            }

        }
        if (white.size() == 1 && white.get(0) instanceof Xa && black.size() == 1 && black.get(0) instanceof Xa) {
            return true;
        }

        // no legal moves stalemate
        if (mate(color) == true && checkForCheck(color) == false) {
            return true;
        }

        return false;

    }

}