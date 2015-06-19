package retodelviajero;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;

public class RetoDelViajero extends Applet {
    private     Ciudad  c[];
    private     Ruta    r[];
    private     Ciudad  inicio;
    private     ArrayList<Ciudad> camino;
    private     int distancia;
    
    
    public void init() {
        setBackground(Color.orange);
        lectorEntrada();
        camino=new ArrayList();
        lectorCamino();
        setFont(new Font("Serif", Font.BOLD, 20));
    }
    
    private void lectorEntrada(){
        try{
            BufferedReader br= new BufferedReader(new FileReader("./entrada.txt"));
            c=new Ciudad[Integer.parseInt(br.readLine())];
            br.readLine();
            r=new Ruta[Integer.parseInt(br.readLine())];
            br.readLine();
            int aux=Integer.parseInt(br.readLine());
            br.readLine();
            String siguienteLinea;
            String coordenadas[];
            for(int i=0;i<c.length;i++){
                siguienteLinea=br.readLine();
                coordenadas=siguienteLinea.split(" ");
                c[i]=new Ciudad(Integer.parseInt(coordenadas[0]),Integer.parseInt(coordenadas[1]));
                br.readLine();
            }
            String datosRuta[];
            for(int i=0;i<r.length;i++){
                siguienteLinea=br.readLine();
                datosRuta=siguienteLinea.split(" ");
                r[i]=new Ruta(c[Integer.parseInt(datosRuta[0])-1],c[Integer.parseInt(datosRuta[1])-1],Integer.parseInt(datosRuta[2]));
                br.readLine();
            }
            inicio=c[aux];
        }catch(IOException e){System.out.println(e.toString());};
    }
    
    private void lectorCamino(){
        boolean rutaValida=true;
        boolean comprobCiud[]=new boolean[c.length];
        for(int i=0;i<comprobCiud.length;i++)
            comprobCiud[i]=false;
        try{
            BufferedReader br= new BufferedReader(new FileReader("./camino.txt"));
            String leerLinea;
            int numCiud;
            while((leerLinea=br.readLine())!=null){
                if((numCiud=Integer.parseInt(leerLinea))>c.length)
                    rutaValida=false;
                comprobCiud[numCiud-1]=true;
                camino.add(c[numCiud-1]);
                br.readLine();
            }
        }catch(IOException e){System.out.println(e.toString());
                              rutaValida=false;}
        ArrayList<Ruta> aux;
        boolean rutaCiudad=false;
        for(int i=0;i<(camino.size()-2);i++){
            for(int j=0;j<(aux=camino.get(i).getRutas()).size();j++){
                if(aux.get(j).getX()!=camino.get(i+1) && aux.get(j).getY()!=camino.get(i+1) && !rutaCiudad)
                    rutaCiudad=false;
                else{
                    if(!rutaCiudad)
                        distancia+=aux.get(j).getCoste();
                    rutaCiudad=true;
                }
            }
            if(!rutaCiudad)
                rutaValida=false;
            rutaCiudad=false;
        }
        
        if(inicio.equals(camino.get(0)) && inicio.equals(camino.get(camino.size())))
            rutaValida=false;
        
        for(int i=0;i<comprobCiud.length;i++)
            if(!comprobCiud[i])
                rutaValida=false;
        
        if(!rutaValida)
            camino=null;
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        for(int i=0;i<c.length;i++){
            g.fillOval(c[i].getX(),c[i].getY() , 5, 5);
        }
        g.setColor(Color.BLUE);
        for(int i=0;i<r.length;i++){
            g.drawLine(r[i].getX().getX(), r[i].getX().getY(), r[i].getY().getX(), r[i].getY().getY());
        }
        g.setColor(Color.red);
        if(camino!=null){
            ArrayList<Ruta> aux;
            for(int i=0;i<camino.size()-1;i++){
                for(int j=0;j<(aux=camino.get(i).getRutas()).size();j++){
                    if(aux.get(j).getX()==camino.get(i+1) || aux.get(j).getY()==camino.get(i+1))
                        g.drawLine(aux.get(j).getX().getX(),aux.get(j).getX().getY(),aux.get(j).getY().getX(),aux.get(j).getY().getY());
                }
            }
        }
        g.drawString("Total Distancia: "+distancia, getWidth()-200, getHeight()-50);
    }
}
