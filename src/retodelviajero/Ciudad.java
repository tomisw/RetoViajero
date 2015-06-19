package retodelviajero;
import java.util.ArrayList;
public class Ciudad {
    private int x;
    private int y;
    private ArrayList<Ruta> rutas;
    Ciudad(int x,int y){
        this.x=x;
        this.y=y;
        rutas=new ArrayList();
    }
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    public void setRuta(Ruta r){
        rutas.add(r);
    }
    public ArrayList<Ruta> getRutas(){
        return rutas;
    }
}
