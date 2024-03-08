
public class Xa extends Fitxa {

    public Xa(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    boolean esPrimerMov = true;

    @Override
    public boolean possibleMov(int x, int y) {
        // no puede capturarse asi mismo
        if (this.mateixColor(Tauler.getFitxa(x, y)) == true) {
            return false;
        }
        // Elefant
        else if (Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 1) {
            return true;
        }
        // ruhk
        else if (Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 0
                || Math.abs(getX() - x) == 0 && Math.abs(getY() - y) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.BLANCO) {
            return "X";
        }
        return "x";
    }

    public int castle(String side) {
        Ruhk ruhk = (Ruhk) Tauler.getFitxa("ruhk" + side, this.getColor());
        int originX = this.getX();
        int originY = this.getY();

        if (this.esPrimerMov != true || ruhk.esPrimerMov != true) {
            System.out.println("No es pot enrocar si el rei o la torre ja s'han mogut");
            return -1;
        }
        if (Tauler.camiLliure(this.getX(), this.getY(), ruhk.getX(), ruhk.getY()) != true) {
            System.out.println("No es pot enrocar a través d'una línia de control");
            return -1;
        }

        if (this.getColor() == Color.BLANCO) {

            if (side.equals("K")) {
                // no pot creuar una línia de control
                if (this.move(5, 7, null) == 0 && this.move(6, 7, null) == 0) {
                    Tauler.setFitxa(ruhk.getX(), ruhk.getY(), null);
                    Tauler.setFitxa(5, 7, ruhk);
                    return 0;
                } else {
                    Tauler.setFitxa(this.getX(), this.getY(), null);
                    Tauler.setFitxa(originX, originY, this);
                    return -1;
                }
            }

            else if (side.equals("Q")) {
                if (this.move(3, 7, null) == 0 && this.move(2, 7, null) == 0) {
                    Tauler.setFitxa(ruhk.getX(), ruhk.getY(), null);
                    Tauler.setFitxa(3, 7, ruhk);
                    return 0;
                } else {
                    Tauler.setFitxa(this.getX(), this.getY(), null);
                    Tauler.setFitxa(originX, originY, this);
                    return -1;
                }
            }
        }

        if (this.getColor() == Color.NEGRO) {
            if (side.equals("K")) {
                if (this.move(5, 0, null) == 0 && this.move(6, 0, null) == 0) {
                    Tauler.setFitxa(ruhk.getX(), ruhk.getY(), null);
                    Tauler.setFitxa(5, 0, ruhk);
                    return 0;
                } else {
                    Tauler.setFitxa(this.getX(), this.getY(), null);
                    Tauler.setFitxa(originX, originY, this);
                    return -1;
                }
            }

            else if (side.equals("Q")) {
                if (this.move(3, 0, null) == 0 && this.move(2, 0, null) == 0) {
                    Tauler.setFitxa(ruhk.getX(), ruhk.getY(), null);
                    Tauler.setFitxa(3, 0, ruhk);
                    return 0;
                } else {
                    Tauler.setFitxa(this.getX(), this.getY(), null);
                    Tauler.setFitxa(originX, originY, this);
                    return -1;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean potMov() {
        int x = this.getX();
        int y = this.getY();

        // Movimientos en diagonal
        if (this.testMov(x - 1, y - 1) || // arriba izquierda
                this.testMov(x + 1, y - 1) || // arriba derecha
                this.testMov(x - 1, y + 1) || // abajo izquierda
                this.testMov(x + 1, y + 1)) { // abajo derecha
            return true;
        }

        // Movimientos rectos
        if (this.testMov(x - 1, y) || // izquierda
                this.testMov(x + 1, y) || // derecha
                this.testMov(x, y + 1) || // abajo
                this.testMov(x, y - 1)) { // arriba
            return true;
        }

        return false;
    }

}