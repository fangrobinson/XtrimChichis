package xtremecraft.mapa;

public class CeldaTierra extends Celda {
	
    public CeldaTierra(int X,int Y){
		
		this.coordenadaX = X;
		this.coordenadaY = Y;
		this.ocupada = false;
		this.tipo = "tierra";
		
	}

}
