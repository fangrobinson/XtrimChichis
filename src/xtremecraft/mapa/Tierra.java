package xtremecraft.mapa;

public class Tierra extends Celda {
	
    public Tierra(int X,int Y){
		
		this.coordenadaX = X;
		this.coordenadaY = Y;
		this.ocupada = false;
		this.tipo = "tierra";
		
	}

}
