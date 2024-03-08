
public class Baidaq extends Fitxa {

    public Baidaq(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    boolean esPrimerMov = true;

    @Override
    public boolean possibleMov(int x, int y) {

        if (Color.esBlanco(this.color)) {

            // 2 espacios delante
            if (this.esPrimerMov == true && this.getY() - y == 2 && this.getX() - x == 0
                    && Tauler.camiLliure(getX(), getY(), x, y) && Tauler.getFitxa(x, y) == null) {
                return true;
            }
            // 1 espacio delante
            if (this.getY() - y == 1 && this.getX() - x == 0 && Tauler.getFitxa(x, y) == null) {
                return true;
            }

            // diagonal
            if (this.getY() - y == 1 && Math.abs(this.getX() - x) == 1 && Tauler.getFitxa(x, y) != null
                    && this.mateixColor(Tauler.getFitxa(x, y)) == false) {
                return true;
            }
        }

        if (Color.esNegro(this.color)) {
            // 2 espacios delante
            if (this.esPrimerMov == true && this.getY() - y == -2 && this.getX() - x == 0
                    && Tauler.camiLliure(getX(), getY(), x, y) && Tauler.getFitxa(x, y) == null) {
                return true;
            }
            // 1 espacios delante
            if (this.getY() - y == -1 && this.getX() - x == 0 && Tauler.getFitxa(x, y) == null) {
                return true;
            }

            // diagonal
            if (this.getY() - y == -1 && Math.abs(this.getX() - x) == 1 && Tauler.getFitxa(x, y) != null
                    && this.mateixColor(Tauler.getFitxa(x, y)) == false) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (Color.esBlanco(this.color)) {
            return "B"; // Representación para una pieza blanca
        }
        return "b"; // Representación para una pieza negra
    }

    @Override
    public boolean potMov() {
        int x = this.getX();
        int y = this.getY();

        if (Color.esBlanco(this.color)) {

            if (this.testMov(x, y - 1)) {
                return true;
            }

            if (this.testMov(x, y - 2)) {
                return true;
            }

            if (this.testMov(x - 1, y - 1)) {
                return true;
            }

            if (this.testMov(x + 1, y - 1)) {
                return true;
            }

        }
        if (Color.esNegro(this.color)) {

            if (this.testMov(x, y + 1)) {
                return true;
            }

            if (this.testMov(x, y + 2)) {
                return true;
            }

            if (this.testMov(x - 1, y - 1)) {
                return true;
            }

            if (this.testMov(x + 1, y + 1)) {
                return true;
            }
        }

        return false;
    }

}