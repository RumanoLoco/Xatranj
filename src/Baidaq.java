
public class Baidaq extends Fitxa {

    public Baidaq(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    boolean esPrimerMov = true;

    @Override
    public boolean possibleMov(int x, int y) {

        if (this.getColor() == Color.WHITE) {

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

        if (this.getColor() == Color.BLACK) {
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
        if (this.getColor() == Color.WHITE) {
            return "B";
        }
        return "b";
    }

    @Override
    public boolean potMov() {
        int x = this.getX();
        int y = this.getY();

        if (this.getColor() == Color.WHITE) {

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
        if (this.getColor() == Color.BLACK) {

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