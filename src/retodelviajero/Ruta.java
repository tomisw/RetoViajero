package retodelviajero;

public class Ruta {
    private Ciudad x;
    private Ciudad y;
    private int coste;
    
    public Ruta(Ciudad x, Ciudad y, int coste) {
        this.x = x;
        this.y = y;
        this.coste = coste;
        x.setRuta(this);
        y.setRuta(this);
    }
    
    public Ciudad getX() {
        return x;
    }

    public Ciudad getY() {
        return y;
    }

    public int getCoste() {
        return coste;
    }

}
