
public class Elefant extends Fitxa {

    public Elefant(Color color, String ID, int startX, int startY) {
        super(color, ID, startX, startY);
    }

    @Override
    public boolean possibleMov(int x, int y) {
        // No puedes capturar tu propia pieza
        if (this.mateixColor(Tauler.getFitxa(x, y))) {
            return false;
        }

        // Movimiento válido para Elefante: en diagonal de dos en dos casillas
        int deltaX = Math.abs(getX() - x);
        int deltaY = Math.abs(getY() - y);
        if (deltaX == 2 && deltaY == 2 && deltaX == deltaY) {
            return true;
        }

        return false;
    }



    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "E";
        }
        return "e";
    }

    @Override
    public boolean potMov() {
        int x = getX();
        int y = getY();

        // Verificar movimientos en diagonal de dos en dos casillas en las cuatro direcciones posibles
        return (movimientoValido(x - 2, y - 2) || // arriba izquierda
                movimientoValido(x - 2, y + 2) || // arriba derecha
                movimientoValido(x + 2, y - 2) || // abajo izquierda
                movimientoValido(x + 2, y + 2));  // abajo derecha
    }

    // Método para verificar si un movimiento es válido (sin salirse del tablero)
    private boolean movimientoValido(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }


}