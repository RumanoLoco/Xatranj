public class Ministre extends Fitxa {

    public Ministre(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    @Override
    public boolean possibleMov(int x, int y) {
        // Verificar si la casilla está ocupada por una ficha del mismo color
        if (this.mateixColor(Tauler.getFitxa(x, y))) {
            return false;
        }

        // Verificar si el movimiento es en diagonal y solo una casilla
        int deltaX = Math.abs(getX() - x);
        int deltaY = Math.abs(getY() - y);
        return deltaX == 1 && deltaY == 1 && deltaX == deltaY;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.BLANCO) {
            return "M";
        }
        return "m";
    }

    @Override
    public boolean potMov() {
        int x = this.getX();
        int y = this.getY();

        // Verificar si puede moverse una casilla en diagonal
        if ((x - 1 >= 0 && y - 1 >= 0 && testMov(x - 1, y - 1)) || // arriba izquierda
                (x - 1 >= 0 && y + 1 <= 7 && testMov(x - 1, y + 1)) || // arriba derecha
                (x + 1 <= 7 && y - 1 >= 0 && testMov(x + 1, y - 1)) || // abajo izquierda
                (x + 1 <= 7 && y + 1 <= 7 && testMov(x + 1, y + 1))) { // abajo derecha
            return true;
        }

        return false;
    }
}
