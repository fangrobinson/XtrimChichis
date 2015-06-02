package xtremecraft.mapa;


public class Aire extends Celda {
		
    public Aire(int X,int Y){
			
		this.coordenadaX = X;
		this.coordenadaY = Y;
		this.ocupada = false;
		this.tipo = "agua";
		
	}
}

