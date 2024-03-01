
public class Faras extends Fitxa {

    public Faras(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    @Override
    public boolean possibleMov(int x, int y) {
        // no puede capturarse asi mismo
        if (this.mateixColor(Tauler.getFitxa(x, y)) == true) {
            return false;
        }

        if (Math.abs(this.getY() - y) == 2 && Math.abs(this.getX() - x) == 1
                || Math.abs(this.getY() - y) == 1 && Math.abs(this.getX() - x) == 2) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "F";
        }
        return "f";
    }

    @Override
    public boolean potMov() {
        int[][] movimientos = {
                {-2, -1}, {-1, -2}, // arriba izquierda
                {2, -1}, {1, -2},   // arriba derecha
                {-2, 1}, {-1, 2},   // abajo izquierda
                {2, 1}, {1, 2}      // abajo derecha
        };

        int x = this.getX();
        int y = this.getY();

        for (int[] movimiento : movimientos) {
            int newX = x + movimiento[0];
            int newY = y + movimiento[1];
            if (testMov(newX, newY)) {
                return true;
            }
        }

        return false;
    }

}