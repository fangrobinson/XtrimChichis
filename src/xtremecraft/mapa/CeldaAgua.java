package xtremecraft.mapa;


public class CeldaAgua extends Celda {
		
    public CeldaAgua(int X,int Y){
			
		this.coordenadaX = X;
		this.coordenadaY = Y;
		this.ocupada = false;
		this.tipo = "agua";
		
	}
}

