
public abstract class Fitxa {

    private final Color color;

    private final String ID;

    private int x, y;

    public boolean esPrimerMov;

    public Fitxa(Color color, String ID, int startX, int startY) {
        this.color = color;
        this.ID = ID;
        this.x = startX;
        this.y = startY;

        if (this.getColor() == Color.WHITE) {
            Tauler.white.add(this);
        } else if (this.getColor() == Color.BLACK) {
            Tauler.black.add(this);
        }
        Tauler.setFitxa(x, y, this);
    }

    public String getID() {
        return this.ID;
    }

    public boolean matchID(String ID) {
        return this.ID.equals(ID);
    }

    public Color getColor() {
        return this.color;
    }

    public boolean mateixColor(Fitxa otroFitxa) {
        if (otroFitxa == null) {
            return false;
        }
        return (this.color == otroFitxa.getColor());
    }

    public int getX() {
        return this.x;
    }

    void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return this.y;
    }

    void setY(int newY) {
        this.y = newY;
    }

    public abstract boolean possibleMov(int x, int y);

    public int move(int x, int y, Fitxa otro) {
        if (this.possibleMov(x, y) != true) {
            return -1;
        }

        Color color = this.getColor();
        int origenX = this.getX();
        int origenY = this.getY();

        if (this.getColor() == Color.WHITE) {
            Tauler.black.remove(otro);
        } else {
            Tauler.white.remove(otro);
        }

        Tauler.setFitxa(origenX, origenY, null);
        Tauler.setFitxa(x, y, this);

        boolean isFirstMoveOG = this.esPrimerMov;
        this.esPrimerMov = false;

        if (Tauler.checkForCheck(color) == true) {
            if (otro != null) {
                if (this.getColor() == Color.WHITE) {
                    Tauler.black.add(otro);
                } else {
                    Tauler.white.add(otro);
                }
            }
            Tauler.setFitxa(origenX, origenY, this);
            Tauler.setFitxa(x, y, otro);
            this.esPrimerMov = isFirstMoveOG;

            return -1;
        }

        if (this instanceof Baidaq) {
            char file = this.getID().charAt(4);
            if (this.getColor() == Color.WHITE && y == 0) {
                Tauler.setFitxa(x, y, null);
                Tauler.white.remove(this);
                Ministre yasMinistre = new Ministre(Color.WHITE, "ministre" + file, x, y);
                System.out.println("Baidaq muerto!");
            } else if (this.getColor() == Color.BLACK && y == 7) {
                Tauler.setFitxa(x, y, null);
                Tauler.black.remove(this);
                Ministre yasMinistre = new Ministre(Color.BLACK, "ministre" + file, x, y);
                System.out.println("Baidaq muerto!");
            }
        }

        return 0;
    }

    public boolean testMov(int x, int y) {
        int origenX = this.getX();
        int origenY = this.getY();
        Fitxa otro;
        boolean isFirst = this.esPrimerMov;

        if (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
            otro = Tauler.getFitxa(x, y);
            if (this.move(x, y, otro) == 0) {
                // Fitxa capturada set posicion original
                Tauler.setFitxa(x, y, otro);
                // Fitxa selecionada set posicion original
                Tauler.setFitxa(origenX, origenY, this);
                esPrimerMov = isFirst;
                if (otro != null) {
                    if (otro.getColor() == Color.WHITE) {
                        Tauler.white.add(otro);
                    } else
                        Tauler.black.add(otro);
                }
                return true;
            }
        }
        return false;
    }

    public String nullToString() {
        return "   ";
    }

    public abstract String toString();

    public abstract boolean potMov();
}