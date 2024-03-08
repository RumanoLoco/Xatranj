
public class Ruhk extends Fitxa {

    public Ruhk(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    boolean esPrimerMov = true;

    @Override
    public boolean possibleMov(int x, int y) {
        // no se puede matar a si mismo
        if (this.mateixColor(Tauler.getFitxa(x, y)) == true) {
            return false;
        }
        // movimiento invalido
        if (Math.abs(getX() - x) != 0 && Math.abs(getY() - y) != 0) {
            return false;
        }
        // movimiento valido
        if (Tauler.camiLliure(getX(), getY(), x, y)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.BLANCO) {
            return "R";
        }
        return "r";
    }

    @Override
    public boolean potMov() {
        int x = this.getX();
        int y = this.getY();

        // izquierda
        while ((--x) >= 0 && y >= 0) {
            if (this.testMov(x, y)) {
                return true;
            }
        }

        // derecha
        x = this.getX(); // Reiniciamos x
        while ((++x) <= 7 && y >= 0) {
            if (this.testMov(x, y)) {
                return true;
            }
        }

        // abajo
        y = this.getY(); // Reiniciamos y
        while (x >= 0 && (++y) <= 7) {
            if (this.testMov(x, y)) {
                return true;
            }
        }

        // arriba
        y = this.getY(); // Reiniciamos y
        while (x <= 7 && (--y) >= 0) {
            if (this.testMov(x, y)) {
                return true;
            }
        }

        return false;
    }

}